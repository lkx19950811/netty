package com.lkx.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 17:41 2019/11/4
 */
public class OldIoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8899);
        String fileName = "E:\\jdk-8u11-linux-x64.tar.gz";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        long total = 0;
        long readCount;
        long start = System.currentTimeMillis();
        while ((readCount=inputStream.read(bytes))>=0){
             total += readCount;
             dataOutputStream.write(bytes);
        }
        long end = System.currentTimeMillis();
        System.out.println("发送总字节数:" + total + ",cost: " + (end - start));
        dataOutputStream.close();
        inputStream.close();
    }
}
