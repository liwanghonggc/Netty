package com.lwh.netty.chp9.demo3.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 该方法每次客户端连接的时候都会调用一次
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //添加一个我们自己的Handler
        pipeline.addLast(new MyServerHandler());
    }
}
