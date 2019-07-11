
package com.sxt.sso.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目： synchronized 和 lock有什么区别？用新的lock有什么好处？你举例子说说
 * 1 原始构成
 * 	synchronized是关键字属于JVM层面
 * 		monitorenter（底层是通过monitor对象来完成，其实wait/notify等方法也是依赖于monitor对象只有在同步块或方法中才能调用wait/notify等方法）
 * 		monitorexit
 * 	Lock 是具体的类（java.util.concurrent.locks.lock）是api层面的锁
 * 2 使用方法
 * 	synchronized 不需要用户手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 * 	ReentrantLock 则需要用户手动释放锁若，若没有主动释放锁，就用可能导致出现死锁现象。
 * 	需要lock()和unlock()方法配合try/finally语句模块来完成。
 * 3 等待是否可中断
 * 	synchronized	不可中断，除非抛出异常或者正常运行完成
 * 	ReentrantLock	可中断，1.设置超方法 tryLock(long timeout, TimeUnit unit)
 * 						  2.lockInterruptibly()放代码块中，调用interrupt()方法可中断
 * 4 加锁是否公平
 * 	synchronized	非公平锁
 *  ReentrantLock	两者都可以，默认被公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 * 5 锁绑定多个条件Condition
 * 	synchronized	没有
 *  ReentrantLock	用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized需要随机唤醒一个线程要么唤醒全部线程
 * @author Administrator
 *
 * 题目2：多线程之间按顺序调用，实现A->B->C 三个线程启动，要求如下：
 * AA打印5次，B打印10次，CC打印15次
 * 紧接着
 * AA打印5次，B打印10次，CC打印15次
 * ...
 * 要求循环10轮
 */

class ReentrantConditions {
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition5 = lock.newCondition();
	private Condition condition10 = lock.newCondition();
	private Condition condition15 = lock.newCondition();
	
	public void print5() {
		lock.lock();
		try {
			while (number != 1) {
				try {
					condition5.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t "+i);
			}
			number = 2;
			condition10.signal();
		} finally {
			lock.unlock();
		}
	}
	public void print10() {
		lock.lock();
		try {
			while (number != 2) {
				try {
					condition10.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t "+i);
			}
			number = 3;
			condition15.signal();
		} finally {
			lock.unlock();
		}
	}
	public void print15() {
		lock.lock();
		try {
			while (number != 3) {
				try {
					condition15.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t "+i);
			}
			number = 1;
			condition5.signal();
		} finally {
			lock.unlock();
		}
	}
	
}
public class SyncAndReentrantLockDemo {
	public static void main(String[] args) {
		ReentrantConditions reentrantConditions = new ReentrantConditions();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				reentrantConditions.print5();
			}
		}, "AA").start();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				reentrantConditions.print10();
			}
		}, "BB").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				reentrantConditions.print15();
			}
		}, "CC").start();
	}
}
