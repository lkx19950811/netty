package com.lkx.netty.fourExampleHeart;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 17:12 2019/10/23
 */
public class MyserverInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(5,7,3));
        pipeline.addLast(new MyServerHandler());
    }
}
