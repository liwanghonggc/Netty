1、IO和NIO区别
1)Java.io中最核心的概念就是流,面向流编程,一个流要么是输入流,要么是输出流,不可能既是输入流又是输出流
2)Java.nio中拥有三个核心概念,Selector、Channel、Buffer,在nio中,我们是面向块(block)或者缓冲区(buffer)编程的,Buffer本身就是一块内存,底层实现上它实际是一个数组.数据的读写都是通过Buffer来实现.

除了数组之外,Buffer还提供了对于数据的结构化访问方式

Java中的8种基本类型都有各自对应的Buffer类型,如IntBuffer、LongBuffer、ByteBuffer等

Channel指的是可以向其写入数据或者从其中读取数据的对象,它类似于java.io中的Stream.
所有数据的读写都是通过Buffer进行的,永远不会出现直接向Channel中写入数据或者直接从Channel中读取数据的情况.
与Stream不同的是,Channel是双向的,一个流只可能是InputStream或者OutputStream,Channel打开之后可以进行读取、写入或是读写.

由于Channel是双向的,因此它能更好的反映出底层操作系统的真实情况,在Linux中,底层操作系统的通道就是双向的.

2、使用NIO进行文件读取所涉及的步骤
1) 从FileInputStream对象获取Channel对象
2) 创建Buffer
3) 将数据读取到Buffer对象中

3、Java的NIO的ByteBuffer
1) 0 <= mark <= position <= limit <= capacity
2) flip方法作用：将limit设置为当前position值,将position设置为0
3) clear方法：将limit设置为capacity,将position设置为0,并没有删除数组中任何元素
4) compact方法：将所有未读的元素复制到buffer起始位置处,将position设为最后未读元素的后面,将limit设为capacity

https://www.zhihu.com/question/57374068