package com.lwh.netty.chp6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lwh
 * @date 2018-09-28
 * @desp Selector选择器
 */
public class NioDemo1 {

    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for(int i = 0; i < ports.length; i++){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            //注册,对什么感兴趣
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口: " + ports[i]);
        }

        while (true){
            int num = selector.select();
            System.out.println("num: " + num);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys: " + selectionKeys);

            Iterator<SelectionKey> itr = selectionKeys.iterator();

            while (itr.hasNext()){
                SelectionKey selectionKey = itr.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    //一定要移除
                    itr.remove();

                    System.out.println("获得客户端的连接: " + socketChannel);
                }
            }
        }
    }
}
