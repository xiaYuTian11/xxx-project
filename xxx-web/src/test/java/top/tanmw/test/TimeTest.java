package top.tanmw.test;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @since 2022/9/13 17:01
 */
public class TimeTest {
    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000000; i++) {
            final long l = System.currentTimeMillis() - System.currentTimeMillis();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotal(TimeUnit.MILLISECONDS));
        StopWatch stopWatch5 = new StopWatch();
        stopWatch5.start();
        for (int i = 0; i < 10000000; i++) {
            StopWatch stopWatch1 = new StopWatch();
            stopWatch1.start();
            stopWatch1.stop();
            final long total = stopWatch1.getTotal(TimeUnit.MILLISECONDS);
        }
        stopWatch5.stop();
        System.out.println(stopWatch5.getTotal(TimeUnit.MILLISECONDS));
        StopWatch stopWatch6 = new StopWatch();
        stopWatch6.start();
        for (int i = 0; i < 10000000; i++) {
            final long l = System.nanoTime() - System.nanoTime();
        }
        stopWatch6.stop();
        System.out.println(stopWatch6.getTotal(TimeUnit.MILLISECONDS));
    }

    @Test
    public void test02() throws Exception {
        int count = 1000000;
        long start1 = System.currentTimeMillis();
        CountDownLatch downLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            ThreadUtil.execute(() -> {
                final long l = System.currentTimeMillis() - System.currentTimeMillis();
                downLatch.countDown();
            });
        }
        downLatch.await();
        System.out.println(System.currentTimeMillis() - start1);
        start1 = System.currentTimeMillis();
        CountDownLatch downLatch1 = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            ThreadUtil.execute(() -> {
                StopWatch stopWatch1 = new StopWatch();
                stopWatch1.start();
                stopWatch1.stop();
                final long total = stopWatch1.getTotal(TimeUnit.MILLISECONDS);
                downLatch1.countDown();
            });
        }
        downLatch1.await();
        System.out.println(System.currentTimeMillis() - start1);
        CountDownLatch downLatch2 = new CountDownLatch(count);
        start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            ThreadUtil.execute(() -> {
                final long l = System.nanoTime() - System.nanoTime();
                downLatch2.countDown();
            });
        }
        downLatch2.await();
        System.out.println(System.currentTimeMillis() - start1);
    }
}
