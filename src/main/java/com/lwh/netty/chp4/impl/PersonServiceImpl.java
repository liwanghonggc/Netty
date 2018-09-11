package com.lwh.netty.chp4.impl;

import com.lwh.netty.chp4.thrift.generated.DataException;
import com.lwh.netty.chp4.thrift.generated.Person;
import com.lwh.netty.chp4.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @author lwh
 * @date 2018-09-11
 * @desp
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Get Client Param: " + username);

        Person person = new Person();
        person.setUsername("李旺红");
        person.setAge(24);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.print("Get Client Param：");
        System.out.println(person.getUsername() + ", " + person.getAge() + ", " + person.isMarried());
    }
}
