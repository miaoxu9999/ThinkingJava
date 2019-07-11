package com.example.demo.example.atomic;

import com.example.demo.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @ClassName AtomicExample5
 * @Description AtomicIntegerFieldUpdater测试
 * @Author miaoxu
 * @Date 2019/7/11 13:41
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile  int count = 100;

    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        if (updater.compareAndSet(example5, 100, 120))
        {
            log.info("update success 1, {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120))
        {
            log.info("update success 2, {}", example5.getCount());
        }
        else
        {
            log.info("update failed 2, {}", example5.getCount());
        }

    }
}
