1、AtomicIntegerFieldUpdater要点总结
1) 更新器更新的必须是int型变量,Integer也不行,不能是包装类型.如果要更新的变量是包装类型,那么可以使用AtomicReferenceFieldUpdater来进行更新
2) 更新器更新的必须是volatile类型变量,确保线程之间共享变量的立即可见性
3) 变量不能是static的,必须是实例变量,因为Unsafe.objectFieldOffset()方法不支持静态变量(CAS操作本质上是通过对象实例的偏移量来直接进行赋值)
4) 更新器只能修改它可见范围的变量,因为更新器是通过反射来得到这个变量,如果变量不可见就会报错

   if (field.getType() != int.class)
         throw new IllegalArgumentException("Must be integer type");

   if (!Modifier.isVolatile(modifiers))
         throw new IllegalArgumentException("Must be volatile type");

2、Netty为什么使用AtomicIntegerFieldUpdater而不使用AtomicInteger来维护引用计数?
   Netty中ByteBuf使用非常多,如果每个ByteBuf内部都维护一个AtomicInteger,会有一定消耗,而AtomicIntegerFieldUpdater是一个全局静态变量,比较高效

3、Netty处理器重要概念
1) Netty的处理器可以分为：入站处理器和出站处理器
2) 入站处理器的顶层是ChannelInboundHandler,出站处理器的顶层是ChannelOutboundHandler
3) 数据处理时常用的各种编解码器本质上都是处理器
4) 编解码器：无论我们向网络中写入的数据是什么数据类型,数据在网络传输时,都是以字节流的形式传递.
            编码encode:将数据由原本的形式转换为字节流的操作
            解码decode:将数据由字节码转换为它原本的格式或是其他格式的操作

5) 编码：本质上是一种出站处理器,因此,编码一定是一种ChannelOutboundHandler
   解码：本质上是一种入站处理器,因此,解码一定是一种ChannelInboundHandler

4、TCP的粘包与拆包

5、Netty编解码器重要结论
1) 无论是编码器还是解码器,其所接收的消息类型必须要与待处理的参数类型一致,否则该编解码器不起作用,会跳至下一个handler
2) 在解码器进行数据解码时,一定要判断缓冲(ByteBuf)中的数据是否足够,否则会产生一些问题