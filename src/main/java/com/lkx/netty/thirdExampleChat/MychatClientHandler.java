package com.lkx.netty.thirdExampleChat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 16:19 2019/10/22
 */
public class MychatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

}
