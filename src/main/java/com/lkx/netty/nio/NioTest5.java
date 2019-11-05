package com.lkx.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 16:30 2019/10/28
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(15);
        byteBuffer.putLong(5000000000L);
        byteBuffer.putDouble(14.1212);
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('我');
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getChar());
    }
}
