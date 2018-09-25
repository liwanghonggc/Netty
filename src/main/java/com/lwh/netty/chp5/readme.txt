1、IO和NIO区别
1)Java.io中最核心的概念就是流,面向流编程,一个流要么是输入流,要么是输出流,不可能既是输入流又是输出流
2)Java.nio中拥有三个核心概念,Selector、Channel、Buffer,在nio中,我们是面向块(block)或者缓冲区(buffer)编程的,Buffer本身就是一块内存,底层实现上它实际是一个数组.数据的读写都是通过Buffer来实现.