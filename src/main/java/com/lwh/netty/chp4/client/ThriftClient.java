package com.lwh.netty.chp4.client;

import com.lwh.netty.chp4.thrift.generated.Person;
import com.lwh.netty.chp4.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author lwh
 * @date 2018-09-11
 * @desp
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByUsername("李旺红");

            System.out.println(person.getUsername() + ", " + person.getAge() + ", " + person.isMarried());

            System.out.println("...............");

            Person newPerson = new Person();
            newPerson.setUsername("许春杰");
            newPerson.setAge(23);
            newPerson.setMarried(false);

            client.savePerson(newPerson);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            transport.close();
        }
    }
}
