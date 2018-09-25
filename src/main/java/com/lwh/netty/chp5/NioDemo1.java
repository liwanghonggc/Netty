package com.lwh.netty.chp5;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author lwh
 * @date 2018-09-25
 * @desp 生成10个随机数
 */
public class NioDemo1 {

    public static void main(String[] args) {
        IntBuffer buf = IntBuffer.allocate(10);

        for(int i = 0; i < buf.capacity(); i++){
            int num = new SecureRandom().nextInt(20);
            buf.put(num);
        }

        //同一个Buffer既可以读,也可以写,读-->写切换或者写-->读切换,需要调用flip方法切换
        buf.flip();

        while(buf.hasRemaining()){
            System.out.println(buf.get());
        }
    }
}
