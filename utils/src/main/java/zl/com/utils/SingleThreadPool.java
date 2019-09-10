package zl.com.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SingleThreadPool
 * @Description TODO
 * @Author zhangliang
 * @Date 2019/6/14 9:37
 * @Version 1.0
 */
public class SingleThreadPool {
    private static ExecutorService pool = null;

    /*初始化线程池*/
    public static void init() {
        if (pool == null) {
            pool = Executors.newSingleThreadExecutor();
        }
    }

    /*提交任务执行*/
    public static void execute(Runnable r) {
        init();
        pool.execute(r);
    }

    /* 关闭线程池*/
    public static void unInit() {
        if (pool == null || pool.isShutdown()) return;
        pool.shutdownNow();
        pool = null;
    }
}