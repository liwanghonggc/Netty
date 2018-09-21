package com.lwh.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lwh
 * @date 2018-09-12
 * @desp
 */
public class BufferDemo {

    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile("D:\\Others\\readme.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        //1.写入数据到Buffer
        int byteRead = channel.read(buf);

        while(byteRead != -1){
            //2.调用flip方法,当向buffer中写入数据时,buffer会记录下写了多少数据,一旦要读取这些数据,需要通过flip方法
            //将Buffer从写模式切换到读模式,在读模式下,可以读取之前写入到buffer的所有数据
            buf.flip();
            while(buf.hasRemaining()){
                //3.从Buffer中读取数据
                System.out.println((char)buf.get());
            }

            //4.一旦读取完了所有数据,就需要清空缓冲区,让它可以再次被写入
            //两种清空方法
            //1).clear,清空整个缓冲区
            //2).compact,清除已经读过的数据,任何未读的数据将被移到缓冲区的起始处,新写入的数据放到缓冲区未读数据的后面
            buf.clear();

            //5.切换回写模式
            byteRead = channel.read(buf);

        }
        file.close();

    }
}
