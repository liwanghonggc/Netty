package com.lwh.netty.chp7.zerocopy.tradition;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author lwh
 * @date 2018-09-30
 * @desp 客户端读取文件发送给服务器
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);

        String fileName = "D:\\test.ev4";
        InputStream ins = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int readCount = 0;
        int total = 0;

        long startTime = System.currentTimeMillis();

        while((readCount = ins.read(buffer)) >= 0){
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节数: " + total + ", 耗时: " + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        ins.close();
    }
}
