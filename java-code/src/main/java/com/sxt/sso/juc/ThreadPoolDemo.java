package com.sxt.sso.juc;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池
 * @author Administrator
 *
 */
public class ThreadPoolDemo {
	
	public static void main(String[] args) {
		
         //默认抛出异常
         //new ThreadPoolExecutor.AbortPolicy()
         //回退调用者
         //new ThreadPoolExecutor.CallerRunsPolicy()
         //处理不来的不处理
         //new ThreadPoolExecutor.DiscardOldestPolicy()
		ExecutorService executorService = new ThreadPoolExecutor(2, 
				5, 
				1, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<>(3), 
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
		try {
			for (int i = 1; i <= 50; i++) {
				executorService.execute(()->{
					System.out.println(Thread.currentThread().getName() + "\t 办理业务");
				});
			}
		} finally {
			executorService.shutdown();
		}
		
	}

}
