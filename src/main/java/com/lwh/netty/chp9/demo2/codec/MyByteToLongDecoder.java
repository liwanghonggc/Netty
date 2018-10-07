package com.lwh.netty.chp9.demo2.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp 将入站数据解码为Long型数据
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoked");

        System.out.println(in.readableBytes());

        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
