package com.lwh.netty.chp1.demo1.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端
 * netty作为http服务器的demo
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        //两个死循环,事件循环组,bossGroup获取连接,将连接转给workerGroup处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于轻松启动Server服务端,辅助类,用于简化Netty的启动流程
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //关联处理器
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    //请求到来之后由handler处理,我们可以定制自己的handler
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
