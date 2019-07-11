package com.sxt.sso.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(4);
		
		for (int i = 1; i <= 8; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
					TimeUnit.SECONDS.sleep(5);
					System.out.println(Thread.currentThread().getName() + "\t 5秒钟离开车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
				
			}, String.valueOf(i)).start();
		}
	}
}
