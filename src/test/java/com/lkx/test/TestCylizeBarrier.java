package com.lkx.test;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 11:26 2019/10/31
 */
public class TestCylizeBarrier {
    @Test
    public void test1() throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        Thread t1 = new Thread(new Thread1(cyclicBarrier,"111"));
        Thread t2 = new Thread(new Thread1(cyclicBarrier,"222"));
        Thread t3 = new Thread(new Thread1(cyclicBarrier,"333"));
        t1.start();
        t2.start();
        t3.start();
        cyclicBarrier.await();
        System.out.println("开始吧");
    }
    class Thread1 implements Runnable{
        CyclicBarrier cyclicBarrier;
        String name;
        Thread1(CyclicBarrier c,String str ){
            cyclicBarrier = c;
            name = str;
        }
        @Override
        public void run() {
            System.out.println(name + "准备好了");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
