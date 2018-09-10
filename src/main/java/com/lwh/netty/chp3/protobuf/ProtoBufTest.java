package com.lwh.netty.chp3.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author lwh
 * @date 2018-09-10
 * @desp
 */
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        //A机器内将对象序列化
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("lwh")
                .setAge(20).setAddress("北京").build();

        byte[] stu2ByteArray = student.toByteArray();

        //B机器内将对象反序列化,跨机器且跨语言
        DataInfo.Student stu = DataInfo.Student.parseFrom(stu2ByteArray);
        System.out.println(stu.getName());
        System.out.println(stu.getAge());
        System.out.println(stu.getAddress());
    }
}
