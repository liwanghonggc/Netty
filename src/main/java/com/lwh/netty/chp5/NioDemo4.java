package com.lwh.netty.chp5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp
 */
public class NioDemo4 {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:\\Software\\IDEA\\Projects\\Netty\\src\\main\\java\\com\\lwh\\netty\\chp5\\input.txt");
        FileOutputStream fos = new FileOutputStream("D:\\Software\\IDEA\\Projects\\Netty\\src\\main\\java\\com\\lwh\\netty\\chp5\\output.txt");

        FileChannel inputChannel = fis.getChannel();
        FileChannel ouputChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(true){
            //如果注释掉这行代码会发生什么情况?
            //如第一次读到47位置,则limit等于capacity1024,position等于47,执行flip方法position等于0,limit等于47,然后write数据之后position也移到47
            //这时如果使用clear方法则position等于0,limit等于capacity,一切正常.如果不用clear,则此时position==limit,读取数据len为0,循环不会停止
            buffer.clear();

            int len = inputChannel.read(buffer);
            System.out.println("read: " + len);

            if(len == -1){
                break;
            }

            buffer.flip();

            ouputChannel.write(buffer);
        }

        inputChannel.close();
        ouputChannel.close();
    }
}
