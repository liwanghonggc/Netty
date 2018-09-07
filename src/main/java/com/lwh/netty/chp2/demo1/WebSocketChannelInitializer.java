package com.lwh.netty.chp2.demo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author lwh
 * @date 2018-09-07
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //WebSocket也是基于HTTP协议的,所以需要HTTP编解码器
        pipeline.addLast(new HttpServerCodec());
        //以块的方式写数据
        pipeline.addLast(new ChunkedWriteHandler());
        //对HTTP消息聚合,对message和content聚合到FullHttpRequest或者FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(8192));
    }
}
