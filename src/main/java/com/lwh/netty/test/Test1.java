package com.lwh.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author lwh
 * @date 2018-09-30
 * @desp 测试Netty的NioEventLoopGroup的默认线程数
 */
public class Test1 {

    public static void main(String[] args) {
        int threadNums = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(threadNums);
    }
}
