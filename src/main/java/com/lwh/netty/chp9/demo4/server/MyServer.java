package com.lwh.netty.chp9.demo4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {

        //两个死循环,bossGroup获取连接,将连接转给workerGroup处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于轻松启动Server服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //关联处理器
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8866).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
