package com.lwh.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;


public class NioSocketDemo {

    private Selector selector;

    public static void main(String[] args) throws IOException{
        NioSocketDemo nio = new NioSocketDemo();
        nio.initServer(8846);
        nio.listenSelector();
    }

    public void initServer(int port) throws IOException{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务端已启动");
    }

    public void listenSelector() throws IOException{
        //轮询监听selector
        while(true){
            //等待客户连接,select模型,多路复用
            this.selector.select();

            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();
                //处理请求
                handler(key);
            }
        }
    }

    private void handler(SelectionKey key) throws IOException{
        if(key.isAcceptable()){
            //处理客户端连接请求
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            //获取客户端连接
            SocketChannel socketChannel = serverChannel.accept();
            socketChannel.configureBlocking(false);
            //接收客户端发送的信息时,需要给通道设置读的权限
            socketChannel.register(selector, SelectionKey.OP_READ);
        }else if(key.isReadable()){
            //处理读的事件
            SocketChannel socketChannel = (SocketChannel) key.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = socketChannel.read(buffer);
            if(len > 0){
                String info = new String(buffer.array(), "UTF-8").trim();
                System.out.println("服务端收到的数据: " + info);
            }else {
                System.out.println("客户端关闭了");
                key.cancel();
            }
        }
    }
}
