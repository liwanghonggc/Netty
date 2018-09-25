package com.lwh.netty.chp5;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp 往外写数据
 */
public class NioDemo3 {

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("test.txt");

        FileChannel channel = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(512);

        byte[] messages = "lwh sayHello".getBytes();

        for(int i = 0; i < messages.length; i++){
            buf.put(messages[i]);
        }

        buf.flip();

        channel.write(buf);

        fos.close();
    }
}
