package com.lkx.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 15:17 2019/10/29
 */
public class NioServer {
    private static Map<String,SocketChannel> clientMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //注册到 selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()){
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "{" + UUID.randomUUID().toString() + "}";
                            System.out.println(key + "连接");
                            clientMap.put(key,client);
                            //删除 selectionKey
                            selectionKeys.remove(selectionKey);

                        }else if (selectionKey.isReadable()){
                            client = (SocketChannel)selectionKey.channel();
                            ByteBuffer reader = ByteBuffer.allocate(1024);

                            int count = client.read(reader);
                            String receiveMessage = null;
                            if (count>0){
                                reader.flip();
                                Charset charset = Charset.forName("GBK");
                                receiveMessage = String.valueOf(charset.decode(reader).array());
                                System.out.println(client + "," + receiveMessage);
                            }

                            String sendKey = null;
                            for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                                if (client == entry.getValue()){
                                    sendKey = entry.getKey();
                                    break;
                                }
                            }
                            for (Map.Entry<String,SocketChannel> entry: clientMap.entrySet()){
                                SocketChannel value = entry.getValue();
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((sendKey + ":" + receiveMessage).getBytes("GBK"));
                                writeBuffer.flip();
                                value.write(writeBuffer);
                            }

                        }else if (selectionKey.isValid()){
                            for (Map.Entry<String,SocketChannel> entry:clientMap.entrySet() ){
                                if (selectionKey.channel() == entry.getValue()){
                                    String key = entry.getKey();
                                    System.out.println(key + "已经移除");
                                    clientMap.remove(entry.getKey());
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                //清除处理完毕的 key
                selectionKeys.clear();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
