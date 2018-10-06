package com.lwh.netty.chp8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author lwh
 * @date 2018-10-06
 * @desp
 */
public class ByteBufDemo2 {

    public static void main(String[] args) {
        //utf-8中,中文占3个字节,英文占一个字节
        ByteBuf byteBuf = Unpooled.copiedBuffer("中文hello world", Charset.forName("utf-8"));

        //堆上面的缓冲,用字节数组存放内容
        if(byteBuf.hasArray()){
            byte[] content = byteBuf.array();
            System.out.println(new String(content, Charset.forName("utf-8")));

            //ByteBuf的类型
            System.out.println(byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            //等于writeIndex - readIndex
            System.out.println(byteBuf.readableBytes());

            for(int i = 0; i < byteBuf.readableBytes(); i++){
                System.out.println((char)byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0, 7, Charset.forName("utf-8")));
        }
    }

}
