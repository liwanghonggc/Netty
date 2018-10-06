package com.lwh.netty.chp8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author lwh
 * @date 2018-10-06
 * @desp ByteBuf使用Demo
 */
public class ByteBufDemo1 {

    public static void main(String[] args) {
        //非池化,用完即销毁
        ByteBuf buffer = Unpooled.buffer(10);

        for(int i = 0; i < 10; i++){
            buffer.writeByte(i);
        }

        for(int i = 0; i < buffer.capacity(); i++){
            System.out.println(buffer.getByte(i));
        }

    }
}
