package com.lwh.netty.chp9.demo4.server;

import com.lwh.netty.chp9.demo4.protocol.PersonProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        String message = new String(content, Charset.forName("utf-8"));
        System.out.println("服务端接收到的数据长度: " + length + ", 内容: " + message);
        System.out.println("服务端接收到的消息数量: " + (++this.count));

        String responseMsg = UUID.randomUUID().toString();
        int responseLength = responseMsg.getBytes().length;
        byte[] responseContent = responseMsg.getBytes("utf-8");

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseLength);
        personProtocol.setContent(responseContent);

        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
