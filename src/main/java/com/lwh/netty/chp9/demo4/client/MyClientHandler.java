package com.lwh.netty.chp9.demo4.client;

import com.lwh.netty.chp9.demo4.protocol.PersonProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 10; i++){
            String message = "send from client";
            byte[] content = message.getBytes(Charset.forName("utf-8"));
            int length = message.getBytes(Charset.forName("utf-8")).length;

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setContent(content);
            personProtocol.setLength(length);

            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        String message = new String(content, Charset.forName("utf-8"));
        System.out.println("客户端接收到的数据长度: " + length + ", 内容: " + message);
        System.out.println("客户端接收到的消息数量: " + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
