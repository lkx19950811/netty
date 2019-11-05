package com.lkx.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 17:53 2019/11/4
 */
public class NewIoServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.setReuseAddress(true);
        socket.bind(socketAddress);
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int readCont = 0;

            while (-1 != readCont){
                try {
                    readCont = socketChannel.read(byteBuffer);

                }catch (Exception e){
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
            //byteBuffer.clear();
        }
    }
}
