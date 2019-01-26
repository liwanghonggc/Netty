package com.lwh.src.ch6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author
 */
public class InBoundHandlerB extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("InBoundHandlerB: " + msg);
//        ctx.fireChannelRead(msg);
        throw new BusinessException("from InBoundHandlerB");
    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.channel().pipeline().fireChannelRead("hello world");
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("InboundHandlerB.exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
