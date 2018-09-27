package com.lwh.netty.chp5;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author lwh
 * @date 2018-09-27
 * @desp 文件锁
 */
public class NioDemo9 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\Software\\IDEA\\Projects\\Netty\\src\\main\\java\\com\\lwh\\netty\\chp5\\output.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        FileLock lock = channel.lock(3, 6, true);
        System.out.println("valid: " + lock.isValid());
        System.out.println("lock type: " + lock.isShared());

        lock.release();

        randomAccessFile.close();
    }
}
