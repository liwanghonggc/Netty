package com.lwh.netty.chp3.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lwh
 * @date 2018-09-10
 * @desp
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        //两个死循环,bossGroup获取连接,将连接转给workerGroup处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于轻松启动Server服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //关联处理器
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    //请求到来之后由handler处理,我们可以定制自己的handler
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
