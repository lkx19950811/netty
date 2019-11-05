package com.lkx.netty.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 16:51 2019/10/29
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys){
                    if (selectionKey.isConnectable()){
                        SocketChannel client = (SocketChannel)selectionKey.channel();

                        if (client.isConnectionPending()){
                            client.finishConnect();

                            ByteBuffer writtenBuffer = ByteBuffer.allocate(1024);
                            writtenBuffer.put((LocalDateTime.now() + "连接成功").getBytes("GBK"));
                            writtenBuffer.flip();
                            client.write(writtenBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(()->{
                               while (true){
                                   try {
                                       writtenBuffer.clear();
                                       InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                       BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                       String sendMessage = bufferedReader.readLine();
                                       writtenBuffer.put(sendMessage.getBytes("GBK"));
                                       writtenBuffer.flip();
                                       client.write(writtenBuffer);
                                   }catch (Exception e){
                                       e.printStackTrace();
                                   }
                               }
                            });
                        }
                        client.register(selector,SelectionKey.OP_READ);
                    }else if (selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int count = client.read(readBuffer);
                        if (count>0){
                            String receiveMessage = new String(readBuffer.array(),0,count);
                            System.out.println(receiveMessage);
                        }
                    }
                }
                selectionKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
