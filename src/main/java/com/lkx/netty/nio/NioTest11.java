package com.lkx.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:04 2019/10/29
 */
public class NioTest11 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength =  2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);
        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true){
            int byteRead = 0;

            while (byteRead < messageLength){
                long r = socketChannel.read(buffers);

                byteRead +=r;
                System.out.println("byteRead: " + byteRead);
                Arrays.stream(buffers).map(buffer-> "position:+" + buffer.position() + " limit:" + buffer.limit())
                        .forEach(System.out::println);

            }
            Arrays.stream(buffers).forEach(Buffer::flip);
            long byteWritten = 0;
            while (byteWritten<messageLength){
                long r = socketChannel.write(buffers);
                byteWritten +=r;
            }
            Arrays.stream(buffers).forEach(Buffer::clear);

            System.out.println("byteRead: " + byteRead + ",byteWritten: " + byteWritten + ",messageLength: " + messageLength);
        }
    }
}
