package com.lkx.netty.sixthProtoBufExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:35 2019/10/24
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if (dataType == MyDataInfo.MyMessage.DataType.PersonType){
            MyDataInfo.Person person = msg.getPerson();
            System.out.println("person:" + person.getName() + "," + person.getAddress() + "," + person.getAge());
        }else if (dataType == MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println("dog:" + dog.getName() +  "," + dog.getAge());
        }else if (dataType == MyDataInfo.MyMessage.DataType.CatType){
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println("cat:" + cat.getName() +  "," + cat.getAge());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
