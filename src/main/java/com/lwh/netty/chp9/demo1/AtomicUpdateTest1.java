package com.lwh.netty.chp9.demo1;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp
 */
public class AtomicUpdateTest1 {
    public static void main(String[] args) {
        Person person = new Person();

        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(() -> {

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.age++);
            });
            thread.start();
        }
    }
}
