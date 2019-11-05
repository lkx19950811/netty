package com.lkx.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 10:01 2019/10/29
 */
public class NioTest8 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input2.txt");
        FileOutputStream outputStream = new FileOutputStream("output2.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outPutChannel = outputStream.getChannel();
        //堆外内存,零拷贝
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
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
