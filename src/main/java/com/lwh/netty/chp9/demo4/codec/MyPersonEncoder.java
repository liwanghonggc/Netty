package com.lwh.netty.chp9.demo4.codec;

import com.lwh.netty.chp9.demo4.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encode invoked");

        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
