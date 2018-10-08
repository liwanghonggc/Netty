package com.lwh.netty.test;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author lwh
 * @date 2018-10-08
 * @desp ByteBuf的toString方法
 */
public class Test3 {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.copiedBuffer("lwh sayHello", Charset.forName("utf-8"));

        //UTF-8
        System.out.println(Charset.defaultCharset());

        System.out.println(buffer.toString(Charset.forName("utf-8")));
    }
}
