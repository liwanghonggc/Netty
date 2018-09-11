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