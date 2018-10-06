demo1 netty作为http服务器的demo
demo2 netty的socket编程例子
demo3 netty的聊天例子
demo4 netty的心跳检测机制

Reactor模式：反应器模式,Netty整体架构是Reactor模式的完整体现

1、传统模式的缺点
1) 一个socket一个线程
2) 服务端能够建立的线程数有限，线程上下文切换也要消耗资源
3) 连接建立好之后，连接上未必时时刻刻都有数据传递，服务端的线程资源浪费

2、Reactor模式的5种角色构成
1) Handle(句柄或是描述符)：本质上表示一种资源，是由操作系统提供的。该资源用于表示一个个事件，比如说文件描述符，或是针对网络编程中的Socket描述符，
   事件既可以来自外部，也可以来自于内部。外部事件比如说客户端提供的连接请求，客户端发送过来的数据等。内部事件比如说操作系统产生的定时器事件等，它
   本质上就是一个文件描述符。Handle是事件产生的发源地。

2) Synchronous Event Demultiplexer(同步事件分离器)：它本身就是系统调用，用于等待事件的发生(事件可能是一个，也可能多个)，调用方在调用它的时候会
   被阻塞，一直阻塞到同步事件分离器上有事件产生为止。对于Linux来说，同步事件分离器指的就是常用的IO多路复用，比如select、poll、epoll等。对应于
   Java Nio的Selector组件，对应的阻塞方法就是select方法。

3) Event Handler(事件处理器)：本身由多个回调方法构成，这些回调方法构成了与应用相关的对于某个事件的反馈机制。Netty相比于Java Nio来说，在事件处理器
   这个角色上进行了升级，它为我们开发者提供了大量的回调方法，供我们在特定事件产生时实现相应的回调方法进行业务逻辑的处理。

4) Concrete Event Handler(具体事件处理器)：它是事件处理器的实现。它本身实现了事件处理器所提供的各个回调方法，从而实现了特定业务的逻辑。它本质上就是
   我们所编写的一个个处理器实现。

5) Initiation Dispatcher(初始分发器)：最核心的，它实际上就是Reactor角色。它本身定义了一些规范，这些规范用于控制事件的调用方式，同时又提供了应用进行
   事件处理器的注册、删除等设施。它本身是整个事件处理器的核心所在，Initiation Dispatcher会通过同步事件分离器来等待事件的发生，一旦事件发生，一旦事件
   发生，Initiation Dispatcher首先会分离出每一个事件(即遍历SelectionKey)，然后调用应用事件处理器，最后调用相关的回调方法来处理事件。


3、Reactor模式的流程
1) 当应用向Initiation Dispatcher注册具体的事件处理器时,应用会标识出该事件处理器希望Initiation Dispatcher在某个事件发生时向其通知该事件,该事件与Handle关联

2) Initiation Dispatcher会要求每个事件处理器向其传递内部的Handle,该Handle向操作系统表示了事件处理器

3) 当所有的事件处理器注册完毕后,应用会调用handle_event方法来启动Initiation Dispatcher的事件循环.这时,Initiation Dispatcher会将每个注册的事件管理器的Handle
   合并起来,并使用同步事件分离器来等待这些事件的发生.比如说,TCP协议层会使用select同步事件分离器操作来等待客户端发送的数据到达连接的socket handle上.

4) 当某个事件源对应的Handle变为ready状态时(比如说TCP Socket变为等待读状态时),同步事件分离器会通知Initiation Dispatcher

5) Initiation Dispatcher会触发事件处理器的回调方法,从而响应这个处于ready状态的Handle.当事件发生时,Initiation Dispatcher会将被事件源激活的Handle作为Key来寻找
   并分发恰当的事件处理器回调方法.

6) Initiation Dispatcher会回调事件处理器的handle event回调方法来执行特定于应用的逻辑(开发者自己所编写的功能),从而响应这个事件,所发生的事件类型可以作为该方法的参数
   并被该方法内部使用来执行额外的特定于服务的分离与分发.


4、ChannelPipeline
1) 它是位于AbstractChannel中的,它是一个双向链表


5、关系总结
1) 一个EventLoopGroup会包含一个或多个EventLoop
2) 一个EventLoop在它的整个生命周期中都只会与唯一一个Thread进行绑定,因此一定不要让任何handler中的耗时线程阻塞该IO线程
3) 所有由EventLoop所处理的各种IO事件都将在它所关联的那个Thread进行处理
4) 一个Channel在它的整个生命周期中只会注册在一EventLoop上,而一个EventLoop只会关联一个Thread,因此Channel中的那些handler方法只会由同一个线程来调用执行,不会有多线程问题,
   不需要在handler中做并发控制
5) 一个EventLoop在运行过程当中,会被分配一个或者多个Channel

重要结论：
1) Netty中,Channel的实现一定是线程安全的,基于此,我们可以存储一个Channel的引用,并且在需要向远程端点发送数据时,通过这个引用来调用Channel的相应方法,即便当时有很多线程都在使用它
   也不会出现多线程问题,而且,消息一定会按照顺序发送出去
2) 我们在业务开发中,不要将长时间执行的耗时任务放入到EventLoop的执行队列中,因为它将会一直阻塞该线程所对应的的所有Channel上的其他执行任务,如果我们需要进行阻塞调用或是耗时操作(实际
   开发中很常见),那么我们需要使用一个专门的EventExecutor(业务线程池)

业务线程池实现方式：
1) 在ChannelHandler的回调方法中,使用自己定义的业务线程池
2) 借助于Netty提供的向ChannelPipeline添加ChannelHandler时调用的addLast方法来传递EventExecutor,默认情况下,调用addLast(handler),ChannelHandler中的回调方法都是由IO线程所
   执行,如果调用了ChannelPipeline addLast(EventExecutorGroup group, ChannelHandler... handlers)方法,那么ChannelHandler中的回调方法就是由参数中的group线程组来执行的

6、JDK的Future、Netty的Future、Promise等关系
1) JDK所提供的Future只能通过手动方式检查执行结果,而这个操作是会阻塞的.Netty则对ChannelFuture进行了增强,通过ChannelFutureListener以回调的方式来获取执行结果,去除了手工检查阻塞结果的操作.
   值得注意的是,ChannelFutureListener的operationComplete方法是由IO线程执行的,因此要注意的是不要在这里执行耗时操作,否则需要通过另外的线程或者线程池来执行.

7、在Netty中有两种发送消息的方式,ctx.channel().writeAndFlush或者ctx.writeAndFlush(),即它可以直接写到channel中,也可以写到与ChannelHandler所关联的那个ChannelHandlerContext中.对于前
   一种方式来说,消息会从ChannelPipeline的末尾开始流动;对于后一种方式来说,消息将从ChannelPipeline中的下一个ChannelHandler开始流动

结论：
1) ChannelHandlerContext与ChannelHandler之间的关联绑定关系是永远不会发生改变的,因此对其进行缓存是没有任何问题的
2) 对于与Channel的同名方法来说,ChannelHandlerContext的方法会产生更短的事件流,所以我们应该在可能的情况下利用这个特性来提升应用性能
