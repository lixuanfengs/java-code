package com.sxt.sso.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyResource1 implements Callable<Map<String, Object>> {
	
	public Map<String, Object> call() throws Exception {
		//休息 5s
		System.out.println(Thread.currentThread().getName() +"\t 进来操作");
		TimeUnit.SECONDS.sleep(5);
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		return map;
	}
	
}
class MyResource2 implements Callable<Integer> {
	
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName() +"\t 进来操作");
		return 1024;
	}
	
}

public class CallableDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Map<String, Object>> futureTask = new FutureTask<>(new MyResource1());
		FutureTask<Integer> futureTask2 = new FutureTask<>(new MyResource2());
		Thread thread = new Thread(futureTask,"t1");
		Thread thread2 = new Thread(futureTask2,"t2");
		thread.start();
		thread2.start();
		System.out.println(futureTask2.get());
		System.out.println(futureTask.get().toString());
		System.out.println("线程执行完毕！！！！");
	}
}
