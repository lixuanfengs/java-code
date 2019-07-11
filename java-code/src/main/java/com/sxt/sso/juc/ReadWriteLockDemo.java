package com.sxt.sso.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
	
	private volatile Map<String, Object> map = new HashMap<>();
	private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	
	public void put(String key, Object value) {
		reentrantReadWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t 写入完成...");
		} finally {
			reentrantReadWriteLock.writeLock().unlock();
		}
	}
	
	public void get(String key) {
		reentrantReadWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在读取...");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t 读取完成: " + result);
		} finally {
			reentrantReadWriteLock.readLock().unlock();
		}
	}
}
/**
 * 多个线程同时操作一个资源类没有任何问题 所以为了满足并发量
 * 读取共享资源应该可以同时运行
 * 但是
 * 如果有一个线程想去写共享资源 就不应该有其他线程可以对资源进行读写
 * 
 * 小总结：
 * 读 读能共存
 * 读 写不能共存
 * 写 写不能共存
 * 写操作 原子+独占 整个过程必须是一个完成的统一整体 中间不允许别分割 被打断
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		for (int i = 1; i <= 5; i++) {
			final int temp = i;
			new Thread(() -> {
				myCache.put(temp+"", temp);
			}, "写入线程").start();
		}
		
		for (int i = 1; i <= 5; i++) {
			final int temp = i;
			new Thread(() ->{
				myCache.get(temp+"");
			}, "读取线程").start();
		}
		
	}
	
}
