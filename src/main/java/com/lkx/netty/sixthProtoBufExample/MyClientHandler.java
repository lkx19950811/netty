package com.lkx.netty.sixthProtoBufExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:47 2019/10/24
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {


    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;
        if (0 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("赵云").setAge(30).setAddress("石家庄").build()).build();
        }else if (1 ==randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setAge(10).setName("小黄").build()).build();
        }else if (2 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setAge(4).setName("花花").build()).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }


}
