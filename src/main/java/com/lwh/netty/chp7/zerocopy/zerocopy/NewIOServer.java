package com.lwh.netty.chp7.zerocopy.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lwh
 * @date 2018-09-30
 * @desp
 */
public class NewIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(8899));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            int readCount = 0;

            while (readCount != -1) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                byteBuffer.rewind();
            }
        }
    }
}
