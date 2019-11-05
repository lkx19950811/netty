package com.lkx.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:53 2019/10/29
 */
public class NioTest12 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();
        System.out.println(SelectorProvider.provider().getClass());

        for (int port : ports) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //非阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口: " + port);
        }
        while (true){
            int num = selector.select();
            System.out.println("num: " + num);

            Set<SelectionKey> selectionKeySets = selector.selectedKeys();
            System.out.println("selectionKeys : " + selectionKeySets);
            Iterator<SelectionKey> iter = selectionKeySets.iterator();
            while (iter.hasNext()){
                SelectionKey selectionKey = iter.next();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //使用完一定要remove
                    iter.remove();

                    System.out.println("获得客户端连接: " + socketChannel);
                }else if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    int byteRead = 0;
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);
                        if (read<=0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteRead += read;
                    }
                    System.out.println("读取: " + byteRead + ",来自于:" + socketChannel);
                    //处理完这个key 一定要remove
                    iter.remove();
                }
            }
        }

    }
}
