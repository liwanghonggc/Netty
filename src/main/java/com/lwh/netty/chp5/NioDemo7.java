package com.lwh.netty.chp5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp 堆外内存,DirectByteBuffer本身这个对象是位于Java堆内存上,它里面含有一个成员变量address,通过它可以定位到堆外内存上的数据,该堆外
 *       内存通常是C/C++通过malloc分配的内存
 *
 *       如果我们使用的是HeapByteBuffer,即不是堆外内存,HeapByteBuffer及其所含数据(如字节数组)都是在Java堆上面,操作系统对其IO操作时,需要将其拷贝一份
 *       到操作系统的某一内存区域,然后该内存区域可以直接和IO设备交互.
 *
 *       如果使用的是DirectByteBuffer,分配的字节数组即数据,就是存在操作系统的内存区域,即堆外内存,操作系统可以直接与其交互,少了一次复制操作,
 *       称为零拷贝
 *
 *       为何操作系统不直接操作Java堆上面的数据?
 *       首先,操作系统是可以直接访问堆上面的数据的.但是操作系统在访问时,Java堆上面的数据可能会发生GC,可能会出现OutOfMemory错误,
 *       而且拷贝一次数据相对于IO操作来说时间消耗较短,综合来说拷贝数据更合算.当DirectByteBuffer被回收时,可以通过address定位到
 *       堆外内存,操作系统将其释放,不会出现堆外内存泄露
 */
public class NioDemo7 {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:\\Software\\IDEA\\Projects\\Netty\\src\\main\\java\\com\\lwh\\netty\\chp5\\input.txt");
        FileOutputStream fos = new FileOutputStream("D:\\Software\\IDEA\\Projects\\Netty\\src\\main\\java\\com\\lwh\\netty\\chp5\\output.txt");

        FileChannel inputChannel = fis.getChannel();
        FileChannel outputChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        while(true){
            buffer.clear();

            int len = inputChannel.read(buffer);

            if(len == -1){
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
