package com.lwh.netty.chp1.demo2.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * netty的socket编程例子
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        //bossGroup用来接收客户端的连接,将连接交给workerGroup处理
        //事件循环组,底层就是一个死循环,作用就是在后序事件循环过程中,在select进行操作时,注册一个个channel
        //它只是做了一些准备工作,可以通过构造方法指定线程数,通常指定为1,默认线程数为 availableProcessors() * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //workerGroup是IO线程组,acceptor完成将bossGroup接收到的连接交给workerGroup,由workerGroup中的一个IO线程来完成回调方法
        //若回调方法处理事件过程,则应当由业务线程池来处理该方法
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            //Netty提供的辅助类,用于一些属性设置
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //方法链编程风格
            //channel方法,一个方法接收class参数,或多或少会用到反射
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.WARN))
                    .childHandler(new MyServerInitializer());

            //真正服务器启动的方法,bind()方法,前面的准备工作都是为bind做准备的
            //sync表示等待future操作完成
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
