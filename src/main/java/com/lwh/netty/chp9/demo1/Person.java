package com.lwh.netty.chp9.demo1;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class Person {
    /**
     * 必须是volatile,不能是static
     */
    volatile int age = 1;
}
