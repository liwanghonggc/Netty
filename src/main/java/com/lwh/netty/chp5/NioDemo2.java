package com.lwh.netty.chp5;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp 传统的IO如何调用相应的方法切换成NIO
 */
public class NioDemo2 {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("test.txt");
        FileChannel channel = fis.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(512);
        channel.read(buf);

        buf.flip();

        while(buf.hasRemaining()){
            byte b = buf.get();
            System.out.println((char)b);
        }

        fis.close();
    }
}
