package com.lwh.netty.chp3.demo1.client;

import com.lwh.netty.chp3.demo1.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author lwh
 * @date 2018-09-10
 * @desp
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    /**
     * 连接处于活动状态时,客户端向服务端发起请求
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.MyMessage message = null;
        int randomInt = new Random().nextInt(3);
        if (0 == randomInt) {
            MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("person").setAge(20).setAddress("北京").build();
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(person).build();
        } else if (1 == randomInt) {
            MyDataInfo.Dog dog = MyDataInfo.Dog.newBuilder().setName("dog").setAge(2).build();
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(dog).build();
        } else {
            MyDataInfo.Cat cat = MyDataInfo.Cat.newBuilder().setName("cat").setCity("nanjing").build();
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(cat).build();
        }

        ctx.channel().writeAndFlush(message);
    }
}
