package com.lwh.netty.chp9.demo4;

/**
 * @author lwh
 * @date 2018-10-07
 * @desp 自定义协议
 */
public class PersonProtocol {

    private int length;

    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
