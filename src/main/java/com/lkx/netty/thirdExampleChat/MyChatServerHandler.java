package com.lkx.netty.thirdExampleChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 15:35 2019/10/22
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    //存放的是所有与服务器建立连接的channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " : " + msg);
        channelGroup.forEach(ch->{
            if (!channel.equals(ch)){
                ch.writeAndFlush(channel.remoteAddress() + " : " + msg + "\n");
            }else {
                ch.writeAndFlush("[me] : " + msg + "\n");
            }
        });
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
        super.handlerAdded(ctx);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 离开\n");
        //netty自动会去寻找组中断掉的channel,其实无需手工的移除
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
        ctx.close();
    }
}
