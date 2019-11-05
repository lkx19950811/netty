package com.lkx.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 9:57 2019/10/29
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i =0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }
        ByteBuffer read = byteBuffer.asReadOnlyBuffer();

    }
}
