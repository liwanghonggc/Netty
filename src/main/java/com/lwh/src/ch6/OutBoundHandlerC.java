package com.lwh.src.ch6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author
 */
public class OutBoundHandlerC extends ChannelOutboundHandlerAdapter {

//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        System.out.println("OutBoundHandlerC: " + msg);
//        ctx.write(msg, promise);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("OutBoundHandlerC.exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
