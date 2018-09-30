package com.lwh.netty.chp1.demo2.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty的socket编程例子
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        //bossGroup用来接收客户端的连接,将连接交给workerGroup处理
        //事件循环组,底层就是一个死循环,作用就是在后序事件循环过程中,在select进行操作时,注册一个个channel
        //它只是做了一些准备工作,可以通过构造方法指定线程数,通常指定为1,默认线程数为 availableProcessors() * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            //Netty提供的辅助类,用于一些属性设置
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
