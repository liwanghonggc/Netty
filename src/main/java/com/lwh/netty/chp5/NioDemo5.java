package com.lwh.netty.chp5;

import java.nio.ByteBuffer;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp Slice Buffer与原有Buffer,Slice Buffer是原有的Buffer快照,对其修改会影响到原Buffer
 */
public class NioDemo5 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for(int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte)i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer slice = buffer.slice();

        for(int i = 0; i < slice.capacity(); i++){
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
