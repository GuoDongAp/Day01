package com.example.text3.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2019/4/28.
 */

public class ThreadManger {
    private static ThreadManger sManger;
    private final ThreadPoolExecutor mExe;

    private ThreadManger() {
        mExe = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ThreadManger getManger() {
        if (sManger == null) {
            synchronized (ThreadManger.class){
                if (sManger == null) {
                    sManger = new ThreadManger();
                }
            }
        }
        return sManger;
    }

    public void execute(Runnable runnable){
        if(runnable==null)return;
        mExe.execute(runnable);
    }
    public void remove(Runnable runnable){
        mExe.remove(runnable);
    }
}
