package com.lkx.test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 16:45 2019/11/5
 */
public class TestCountDown {
    private static List<Integer> list = new ArrayList<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(30);
    @Before
    public void init(){
        list.add(1);
        list.add(2);
        list.add(3);
    }
    @Test
    public void test() throws Exception {
        List<Future<Integer>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (Integer i : list){
            Future<Integer> future = threadPool.submit(() -> {
                System.out.println("正在工作:" + i);
                Thread.sleep(1000);
                countDownLatch.countDown();
                return i;
            });
            futures.add(future);
        }
        countDownLatch.await();
        System.out.println("等待完成");
        for (Future<Integer> future : futures){
            System.out.println("获得结果: " + future.get());
        }

    }

}
