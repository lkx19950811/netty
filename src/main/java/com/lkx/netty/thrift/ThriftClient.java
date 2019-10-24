package com.lkx.netty.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 15:50 2019/10/24
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFastFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            tTransport.open();

            Person person = client.getPersonByUserName("张三");

            System.out.println(person.getAge());
            System.out.println(person.getUserName());
            System.out.println(person.isMarried());

            Person person2 = new Person();
            person2.setUserName("赵云");
            person2.setMarried(false);
            person2.setAge(18);
            client.savePerson(person2);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            tTransport.close();
        }
    }
}
