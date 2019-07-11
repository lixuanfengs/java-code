package com.sxt.sso.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo { //ABA问题解决 AtomicStampedReference
	
	static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);
	public static void main(String[] args) {
		
		new Thread(() -> {
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
		},"t1").start();
		
		new Thread(() -> {
			//暂停1秒钟t2线程,保证上面的t1线程完成了一次ABA操作
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
		}, "t2").start();
		
		//暂停一会儿线程
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("=========以下是ABA问题的解决==========");
		
		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 第3次版本号："+stamp);
			//暂停1秒钟t3线程
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			atomicStampedReference.compareAndSet(100, 1001, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t 第二次版本号"+atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(1001, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t 第三次版本号"+atomicStampedReference.getStamp());
		}, "t3").start();
		
		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 第1次版本号："+stamp);
			//暂停3秒钟t4线程  保证上面的t3线程完成了一次ABA操作
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean flag = atomicStampedReference.compareAndSet(100, 101, stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t修改成功否："+ flag+ "\t 当前最新实际版本号："+atomicStampedReference.getStamp());
			
			System.out.println(atomicStampedReference.getReference());
			
		}, "t4").start();
		
	}

}
