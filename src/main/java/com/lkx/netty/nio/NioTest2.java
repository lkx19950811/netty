package com.lkx.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:31 2019/10/28
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();

        while (byteBuffer.remaining() >0){
            byte b = byteBuffer.get();
            System.out.println("Character :" + (char)b);
        }
        fileChannel.close();
    }
}
