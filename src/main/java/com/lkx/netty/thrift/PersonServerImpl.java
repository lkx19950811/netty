package com.lkx.netty.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 15:19 2019/10/24
 */
public class PersonServerImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUserName(String userName) throws DataException, TException {
        System.out.println("Cot Client param: " + userName);
        Person person = new Person();
        person.setUserName(userName);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("cot client param: ");
        System.out.println(person.getUserName());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
