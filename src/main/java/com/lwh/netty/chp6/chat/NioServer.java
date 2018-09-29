package com.lwh.netty.chp6.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author lwh
 * @date 2018-09-29
 * @desp 聊天小程序
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_READ);

        while (true){
            try{

                //该方法阻塞,直到监听到它感兴趣的事情,返回一个整数,表示它所关注到的事件的数量
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;

                    try {
                        if(selectionKey.isAcceptable()){
                            ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
