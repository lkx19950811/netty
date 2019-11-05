package com.lkx.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 13:47 2019/10/28
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outPutChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        while (true){
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if (-1==read){
                break;
            }
            byteBuffer.flip();
            outPutChannel.write(byteBuffer);
         }
        outPutChannel.close();
        inputChannel.close();
    }
}
