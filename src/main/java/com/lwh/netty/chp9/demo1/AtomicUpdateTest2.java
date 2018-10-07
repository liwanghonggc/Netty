package com.lwh.netty.chp9.demo1;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class AtomicUpdateTest2 {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");

        Person person = new Person();

        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
            });

            thread.start();
        }
    }
}
