package com.lwh.netty.chp1.demo4.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //netty提供的空闲检测的handler,5,7,10三个参数分别为netty检测该时间段内有没有发生读事件、写事件、读写事件
        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));

        pipeline.addLast(null);
    }
}
