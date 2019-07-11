package com.sxt.sso.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {System.out.println("龙珠已经集齐，召唤神龙..........");});
		for (int i = 1; i <= 7; i++) {
			final int temp = i;
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + "\t 收集到第"+temp+"龙珠了");
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();;
		}
	}
	
}
