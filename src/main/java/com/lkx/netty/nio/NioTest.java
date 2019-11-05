package com.lkx.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 10:52 2019/10/28
 */
public class NioTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("capacity: " + buffer.capacity());
        for (int i=0;i<5;i++){
            int randomNum = new SecureRandom().nextInt(20);
            buffer.put(randomNum);
        }
        System.out.println("before flip   limit: " + buffer.limit());
        buffer.flip();
        System.out.println("after flip   limit: " + buffer.limit());
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
            System.out.println("position: " + buffer.position());
        }
    }
}
