1、通过索引来访问ByteBuf时并不会改变真实的读索引与写索引,我们可以通过ByteBuf的readerIndex()与writerIndex()来修改它的读写索引.

2、Netty ByteBuf所提供的三种缓冲区类型
1) heap buffer
2) direct buffer
3) composite buffer,复合缓冲区,容器,里面可以容纳heap buffer或者direct buffer

Heap Buffer堆缓冲区：
1) 这是最常用的类型,ByteBuf将数据存储到JVM堆空间中,并且将实际的数据存放到byte array中来实现.
2) 优点：数据存储在JVM堆中,因此可以快速创建与释放,并且它提供了直接访问内部字节数组的方法
3) 缺点：每次读写数据时,都需要先将数据复制到直接缓冲区再进行网络传输

Direct Buffer(直接缓冲区):
1) 在堆之外直接分配内存空间,直接缓冲区不会占用堆的内存空间,因为它是由操作系统在本地内存进行的数据分配
2) 在使用Socket进行数据传输时,性能非常好,因为数据是直接位于操作系统的本地内存中,不需要复制到直接缓冲区中,性能很好
3) 因为Direct Buffer是直接在操作系统内存中的,因此内存空间的分配与释放要比堆空间更加复杂,而且速度要慢一些

Netty通过提高内存池来解决这个问题.直接缓冲区并不支持通过字节数组的方式来访问其内部存放的数据

重点：对于后端的业务消息的编解码来说,推荐使用Heap ByteBuf
     对于IO通信线程在读写缓冲区时,推荐使用Direct ByteBuf

Composite Buffer(复合缓冲区)

3、JDK的ByteBuffer与Netty的ByteBuf之间的差异对比
1) Netty的ByteBuf采用了读写索引分离的策略(readerIndex与writerIndex),一个初始化(里面尚未有数据)的ByteBuf的readerIndex和writerInde
   都为0
2) 当读索引与写索引位于同一个位置时,如果继续读,就会抛出IndexOutOfBoundsException
3) 对于ByteBuf的任何读写操作都会分别单独维护读索引与写索引,maxCapacity最大容量默认值就是Integer.MAX_VALUE.
4) Netty的ByteBuf可以自动扩容,ByteBuffer不支持自动扩容(其byte数组是final的),需要开发者手动new新的ByteBuffer

JDK的ByteBuffer的缺点：
1) final byte[] bb:这是JDK中ByteBuffer用于存储数据的对象声明,可以看到,其字节数据是被声明为final的,长度固定不变,一旦分配好,就不能动态
   扩容与收缩,而且当待存储的数据很大时就很有可能出现IndexOutOfBoundsException.如果ByteBuffer空间不足,只有创建一个新的ByteBuffer对象,
   然后将之前的ByteBuffer中的数据复制过去,这一切操作都需要开发者自己完成
2) ByteBuffer只使用一个position操作指针来标识位置信息,在进行读写切换时就需要调用flip方法或者rewind方法,使用起来不方便

Netty的ByteBuf的优点：
1) 存储字节的数组是动态的,其最大值是Integer.MAX_VALUE,这里的动态性具体体现在write方法中,write方法在执行时会判断buffer容量,如果不足则自动扩容.
2) ByteBuf的读写索引是完全分开的,使用起来很方便

4、Netty的ByteBuf的引用计数