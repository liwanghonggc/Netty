package com.lwh.netty.chp5;

import java.nio.ByteBuffer;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp 只读Buffer,我们可以随时将一个普通的Buffer调用asReadOnlyBuffer方法返回一个只读Buffer
 *       但不能将一个只读Buffer转为读写Buffer
 */
public class NioDemo6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for(int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());

        //执行下面报错
        readOnlyBuffer.put((byte)3);
    }
}
