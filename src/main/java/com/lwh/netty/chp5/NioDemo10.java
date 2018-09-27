package com.lwh.netty.chp5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author lwh
 * @date 2018-09-27
 * @desp 关于Buffer的Scattering与Gathering
 *       Scattering使用场景,自定义协议时,如定义第一个header长为10字节,第二个header长为5字节,则可以使用一个10字节的Buffer和一个5字节的Buffer来接收该数据,
 *       这样就天然的将数据分开接收了
 */
public class NioDemo10 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while(true){
            int bytesRead = 0;

            while(bytesRead < messageLength){
                long r = socketChannel.read(buffers);
                bytesRead += r;

                System.out.println("bytesRead: " + bytesRead);

                Arrays.asList(buffers).stream().map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit()).forEach(System.out::println);
            }


            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            long bytesWriten = 0;
            while(bytesWriten < messageLength){
                long w = socketChannel.write(buffers);
                bytesWriten += w;
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            System.out.println("bytesRead: " + bytesRead + ", bytesWriten: " + bytesWriten + ", messageLength: " + messageLength);
        }

    }
}
