package com.lkx.netty.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:09 2019/10/22
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //把 httpRequestDecoder 和 httpResponseEncoder 合二为一
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("testServerHttpHandler",new TestServerHandler());
    }
}
