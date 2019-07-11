package com.sxt.sso.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/**
 * Executor 管理多个异步任务的执行，
 * 而无需程序员显式地管理线程的生命周期。
 * 这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
 * @author Administrator
 *
 */
class MyThread implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "这是一 Callable<> 接口实现。。。。。。";
	}
	
}
class MyThread2 implements Runnable{


	@Override
	public void run() {
		System.out.println("这是一 Runnable 接口实现。。。。。。");
	}
	
}
public class ThreadDemo {
	
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		MyThread2 myThread2 = new MyThread2();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		FutureTask<String> futureTask = new FutureTask<>(myThread);
		Thread s = new Thread(futureTask);
		s.setDaemon(true);
		s.start();
		try {
			System.out.println(futureTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------");
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executorService.execute(myThread2);
		}
		executorService.shutdown();
	}
	
}
