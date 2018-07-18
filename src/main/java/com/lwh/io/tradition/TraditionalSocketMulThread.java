package com.lwh.io.tradition;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TraditionalSocketMulThread {

    public static void main(String[] args) throws Exception{
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8235);
        System.out.println("服务端启动了");
        while (true){
            //获取客户端套接字
            Socket socket = serverSocket.accept();

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("有新客户端连接上来了");
                        InputStream is = socket.getInputStream();
                        byte[] buf = new byte[1024];
                        while(true){
                            int len = is.read(buf);
                            if(len != -1){
                                String message = new String(buf, 0, len);
                                System.out.println(message);
                            }else {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
