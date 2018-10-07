package com.lwh.netty.chp9.demo2.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp 将入站接收到的Long型数据转为String
 */
public class MyLongToStringDecoder extends MessageToMessageDecoder<Long> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
        System.out.println("MyLongToStringDecoder decode invoked");
        out.add(String.valueOf(msg));
    }
}
