package com.lkx.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 16:39 2019/10/28
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i=0;i<buffer.capacity();++i){
            buffer.put((byte)i);
        }
        buffer.position(2);
        buffer.limit(6);
        ByteBuffer slideBuffer = buffer.slice();
        for (int i=0;i<slideBuffer.capacity();++i){
            byte b = slideBuffer.get(i);
            b *=2;
            slideBuffer.put(i,b);
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
