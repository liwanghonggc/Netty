package com.lwh.netty.chp9.demo2.client;

import com.lwh.netty.chp9.demo2.codec.MyByteToLongDecoder;
import com.lwh.netty.chp9.demo2.codec.MyByteToLongDecoder2;
import com.lwh.netty.chp9.demo2.codec.MyLongToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyByteToLongDecoder2());

        pipeline.addLast(new MyLongToByteEncoder());

        //添加一个我们自己的Handler
        pipeline.addLast(new MyClientHandler());
    }
}
