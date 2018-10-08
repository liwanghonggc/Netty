package com.lwh.netty.chp9.demo4.server;

import com.lwh.netty.chp9.demo4.codec.MyPersonDecoder;
import com.lwh.netty.chp9.demo4.codec.MyPersonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyPersonDecoder());
        pipeline.addLast(new MyPersonEncoder());

        pipeline.addLast(new MyServerHandler());
    }
}
