package com.lwh.netty.chp9.demo2.server;

import com.lwh.netty.chp9.demo2.codec.MyByteToLongDecoder;
import com.lwh.netty.chp9.demo2.codec.MyByteToLongDecoder2;
import com.lwh.netty.chp9.demo2.codec.MyLongToByteEncoder;
import com.lwh.netty.chp9.demo2.codec.MyLongToStringDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //将从客户端收到的信息转为Long类型
        pipeline.addLast(new MyByteToLongDecoder2());

        pipeline.addLast(new MyLongToByteEncoder());

        pipeline.addLast(new MyLongToStringDecoder());

        //添加一个我们自己的Handler
        pipeline.addLast(new MyServerHandler());
    }
}
