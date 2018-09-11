Thrift使用demo

Protobuf主要用来定义消息,传输消息的载体可以使用Netty
而Thrift可以用来定义消息,也提供了传输功能,也可以使用Netty作为底层传输的载体

Thrift由FaceBook开发,主要用于各个服务之间的RPC通信,支持跨语言

Thrift是一个典型的CS架构(客户端/服务端)结构,客户端和服务端可以使用不同的语言开发,因此需要一种中间语言来关联客户端和服务端的语言,这种语言就是IDL(Interface Description Language)

Thrift不支持无符号类型,支持的数据类型有byte、i16、i32、i64、double、string

Thrift支持的三类组件：结构体(对应于protobuf的message,Java中类)、service(接口,方法参数、名字等)、exception

数据传输使用socket

struct结构体,类似C语言
枚举,类似Java
异常exception,规则和struct类似
服务service,若干个方法的集合,和Java的interface类似,Thrift定义的服务相当于Java中创建Interface一样,创建的service经过代码生成命令之后就会生成客户端和服务端的框架代码
常量使用const定义
命名空间namespace,类似Java的package
文件包含include,类似import
Thrift注释,#
可选与必选,required或者optional

在命令行里执行下面命令,可以生成客户端服务端代码
thrift -r --gen java data.thrift

Thrift传输格式：
TBinaryProtocol      二进制格式,较常用
TCompactProtocol     压缩格式,用的最多
TJSONProtocol        JSON格式
TSimpleJSONProtocol  提供JSON只写协议,生成的文件很容易通过脚本语言解析,不常使用
TDebugProtocol       使用易懂的可读的文本格式,用于debug


Thrift数据传输方式：
TSocket              阻塞式Socket
TFramedTransport     以frame为单位进行传输,非阻塞式服务中使用,建议使用这个
TFileTransport       以文件形式进行传输
TMemoryTransport     将内存用于IO,Java实现时内部实际使用了简单的ByteArrayOutputStream
TZlibTransport       使用Zlib进行压缩,与其他传输方式联合使用,当前无Java实现


Thrift支持的服务模型：
TSimpleServer        简单的单线程服务模型,常用于测试
TThreadPoolServer    多线程服务模型,使用标准的阻塞式IO,一个请求来了起一个线程
TNonblocklingServer  多线程服务模型,使用非阻塞式IO(需使用TFramedTransport的数据传输方式)
THsHaServer          THsHa引入了线程池去处理,其模型把读写任务放到线程池去处理;Half-sync/Half-async的处理模式(半同步半异步),Half-async是在处理IO事件上,Half-sync用于handler对rpc的同步处理
                     它是TNonbloclingServer的扩展,也需要使用TFramedTransport的数据传输方式,建议使用这个
