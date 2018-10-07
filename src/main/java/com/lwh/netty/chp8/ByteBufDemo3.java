package com.lwh.netty.chp8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * @author lwh
 * @date 2018-10-06
 * @desp Netty ByteBuf所提供的三种缓冲区类型
 */
public class ByteBufDemo3 {

    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);

        ByteBuf directBuf = Unpooled.directBuffer(8);

        compositeByteBuf.addComponents(heapBuf, directBuf);

        Iterator<ByteBuf> itr = compositeByteBuf.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
