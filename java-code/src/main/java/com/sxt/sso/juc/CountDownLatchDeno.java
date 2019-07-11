package com.sxt.sso.juc;

import java.util.concurrent.CountDownLatch;

import com.sxt.sso.juc.enums.CountryEnum;

public class CountDownLatchDeno {

	public static void main(String[] args) {
		closeDoor();
	}
	
	private static void closeDoor () {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		
		for (int i = 1; i <= 6; i++) {
			new Thread(() ->{
				System.out.println(Thread.currentThread().getName() + "\t 上完自习课");
				countDownLatch.countDown();
			}, CountryEnum.forEach(i).getName()).start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "\t 班长关门走人");
	}
}
