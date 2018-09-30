package com.lwh.netty.chp7.zerocopy.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lwh
 * @date 2018-09-30
 * @desp
 */
public class NewIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        String fileName = "D:\\test.ev4";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        //This method is potentially much more efficient than a simple loop
        //that reads from this channel and writes to the target channel.  Many
        //operating systems can transfer bytes directly from the filesystem cache
        //to the target channel without actually copying them.
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数: " + transferCount + ", 耗时: " + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
