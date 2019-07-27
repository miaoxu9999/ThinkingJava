package com.example.demo.example.commonUnsafe;

import com.example.demo.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName StringExample1
 * @Description 验证String不是线程安全的类
 * @Author miaoxu
 * @Date 2019/7/11 10:43
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class StringExample1 {
    //请求总数
    public static int clientTotal = 5000;
    //同时请求的线程数量
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService  = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdownNow();
        log.info("count:{}", stringBuilder.length());

    }

    public static void update()
    {
        stringBuilder.append("1");
    }
}
