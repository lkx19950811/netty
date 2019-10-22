package com.lkx.netty.thirdExampleChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 16:00 2019/10/22
 */
public class MychatClient1 {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup).channel(NioSocketChannel.class).
                    handler(new MychatClientInitializer());
            Channel channel = bootstrap.connect("localhost",8899).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            for (;;){
                channel.writeAndFlush(br.readLine() + "\r\n");
            }
        }finally {
            workGroup.shutdownGracefully();
        }
    }
}
