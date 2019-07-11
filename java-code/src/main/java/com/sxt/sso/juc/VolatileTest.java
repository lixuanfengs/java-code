package com.sxt.sso.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
	
	volatile int number = 0;
	
	public void addTo60 () {
		this.number = 60;
	}
	// synchronized 
	public void addPlusPlus () {
		this.number++;
	}
	
	AtomicInteger ati = new AtomicInteger();
	public void AtomicIntegers () {
		 ati.getAndIncrement();
	}
}
/*
1 验证volatile的可见性
    1.1 加入int number=0，number变量之前根本没有添加volatile关键字修饰,没有可见性
    1.2 添加了volatile，可以解决可见性问题
2 验证volatile不保证原子性
    2.1 原子性是不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割。
    需要整体完成，要么同时成功，要么同时失败。
    2.2 volatile不可以保证原子性演示
    2.3 如何解决原子性
        *加sync
        *使用我们的JUC下AtomicInteger
* */
public class VolatileTest {
	
	public static void main(String[] args) {
		MyData myData = new MyData();
		for (int i = 1; i <=20 ; i++) {
			new Thread(() -> {
				for (int j = 1; j <= 1000; j++) {
					myData.addPlusPlus();
					myData.AtomicIntegers();
				}
			},String.valueOf(i)).start();
		}
		//线程数量大于2是 进入此方法
		while(Thread.activeCount() > 2) {
			//就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
			//让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"\t int finnaly number value: "+ myData.number);
		System.out.println(Thread.currentThread().getName()+"\t AtomicInteger finnaly number value: "+ myData.ati);
	}
	//volatile 可以保证可见性，及时通知其他线程，主物理内存的值已经被修改.
	public static void seeOkByVolatile() {
		MyData mydata = new MyData();	
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"\t come in");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mydata.addTo60();
			System.out.println(Thread.currentThread().getName()+"\t update number value:" +mydata.number);
		}, "AAA").start();
		
		while(mydata.number == 0) {
			//System.out.println(Thread.currentThread().getName()+"\t update number value:" +mydata.number);
		}
		System.out.println(Thread.currentThread().getName()+"\t mission is over");
	}
	
}
