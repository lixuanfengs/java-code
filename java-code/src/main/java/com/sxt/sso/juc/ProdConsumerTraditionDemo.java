package com.sxt.sso.juc;
/**
 * 共享资源调用类
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
	private int num=0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	/**
	 * 生产者   
	 * @throws InterruptedException 
	 */
	public void increment() throws InterruptedException {
		lock.lock();
		try {
			//判断
			while (num != 0) {
				// 等待 不生产
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "\t " + num);
			// 干活
			num++;
			//通知唤醒
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void delcrement() throws InterruptedException {
		lock.lock();
		try {
			//判断
			while (num == 0) {
				// 等待 不消费
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "\t " + num);
			// 干活
			num--;
			//通知唤醒
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

/**
* Description
 * 一个初始值为0的变量 两个线程交替操作 一个加1 一个减1来5轮
 * 
 * 1 	线程		操作（方法） 	资源类
 * 2	判断		干活			通知
 * 3 	防止虚假唤醒机制		
 */

public class ProdConsumerTraditionDemo {

	public static void main(String[] args) {
		
		ShareData shareData = new ShareData();
		new Thread(() -> {
			for (int i = 0; i <=3; i++) {
				try {
					shareData.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();
		
		new Thread(() -> {
			for (int i = 0; i <=3; i++) {
				try {
					shareData.delcrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();
	}
	
}
