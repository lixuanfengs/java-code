package com.sxt.sso.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/**
 * 自旋锁好处：循环比较获取直到成功为止，没有类似于 wait 的堵塞
 * 
 * 通过 CAS 完成自旋锁，A 线程先进来调用myLock方法自己持有锁5秒钟，
 * B随后进来发现当前线程持有锁，不是null，所以只能通过自旋锁等待，知道A 释放锁后B随后抢到
 * 
 * @author Administrator
 *
 */ 
public class SpinLockDemo {

	AtomicReference<Thread> atomicReference = new AtomicReference<>();
	public void myLock() {
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName()+"\t come in O(∩_∩)O哈哈~");
		while(!atomicReference.compareAndSet(null, thread)) {
			
		}
	}
	public void myUnLock() {
		Thread thread = Thread.currentThread();
		atomicReference.compareAndSet(thread, null);
		System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock()");
	}
	public static void main(String[] args) {
		SpinLockDemo spinLockDemo = new SpinLockDemo();
		new Thread(() -> {
			spinLockDemo.myLock();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spinLockDemo.myUnLock();
		}, "t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			spinLockDemo.myLock();
			spinLockDemo.myUnLock();
		}, "t2").start();
		
	}
	
}
