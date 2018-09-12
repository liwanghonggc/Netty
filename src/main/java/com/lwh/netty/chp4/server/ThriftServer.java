package com.lwh.netty.chp4.server;

import com.lwh.netty.chp4.impl.PersonServiceImpl;
import com.lwh.netty.chp4.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @author lwh
 * @date 2018-09-11
 * @desp
 */
public class ThriftServer {

    public static void main(String[] args) throws Exception{
        //服务端与客户端的socket连接
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);

        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        //二进制压缩协议
        arg.protocolFactory(new TCompactProtocol.Factory());

        //传输层用到的对象,底层以什么形式传输
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));
        //THsHaServer,半同步半异步
        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server Started");

        //serve方法死循环,异步非阻塞
        server.serve();
    }
}
