package com.lwh.src;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * 服务端
 * netty作为http服务器的demo
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {

        //两个死循环,事件循环组,bossGroup获取连接,将连接转给workerGroup处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于轻松启动Server服务端,辅助类,用于简化Netty的启动流程
            ServerBootstrap b = new ServerBootstrap();
            //关联处理器
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    //给后面连接设置TCP的基本属性
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //创建连接时,绑定一些属性
                    .childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
                    .handler(new ServerHandler())
                    //请求到来之后由handler处理,我们可以定制自己的handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                        }
                    });

            ChannelFuture channelFuture = b.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
