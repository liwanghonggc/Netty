package com.lwh.netty.chp7.zerocopy.tradition;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lwh
 * @date 2018-09-29
 * @desp 零拷贝例子对比
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try{
                byte[] byteArray = new byte[4096];
                while(true){
                    //实际读到的字节数
                    int read = dataInputStream.read(byteArray, 0, byteArray.length);
                    if(read == -1){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
