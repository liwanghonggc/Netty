package com.lwh.netty.chp3.demo1.client;

import com.lwh.netty.chp3.demo1.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lwh
 * @date 2018-09-10
 * @desp
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    /**
     * 连接处于活动状态时,客户端向服务端发起请求
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("lwh").setAge(20).setAddress("北京").build();

       ctx.channel().writeAndFlush(person);
    }
}
