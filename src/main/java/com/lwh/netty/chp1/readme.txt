demo1 netty作为http服务器的demo
demo2 netty的socket编程例子
demo3 netty的聊天例子
demo4 netty的心跳检测机制

Reactor模式：反应器模式,Netty整体架构是Reactor模式的完整体现

1、传统模式的缺点
1) 一个socket一个线程
2) 服务端能够建立的线程数有限，线程上下文切换也要消耗资源
3) 连接建立好之后，连接上未必时时刻刻都有数据传递，服务端的线程资源浪费
