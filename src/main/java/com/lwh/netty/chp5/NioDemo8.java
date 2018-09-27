package com.lwh.netty.chp5;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lwh
 * @date 2018-09-27
 * @desp 内存映射文件
 */
public class NioDemo8 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\Software\\IDEA\\Projects\\Netty\\src\\main\\java\\com\\lwh\\netty\\chp5\\output.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        //位于堆外内存,内存对文件的一个映射
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte)'a');
        mappedByteBuffer.put(3, (byte)'b');

        randomAccessFile.close();
    }
}
