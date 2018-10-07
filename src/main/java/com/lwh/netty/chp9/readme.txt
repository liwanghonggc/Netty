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