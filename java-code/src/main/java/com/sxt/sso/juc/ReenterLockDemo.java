package com.sxt.sso.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
	public synchronized void sendSms() throws Exception{
		System.out.println(Thread.currentThread().getName() + "\tsendSms");
		senMail();
	}
	public synchronized void senMail() throws Exception{
		 System.out.println(Thread.currentThread().getName()+"\tsendEmail");
	}
	public void run() {
		get();
	}
	
	Lock reenterLock = new ReentrantLock();
	public void get() {
		reenterLock.lock();
		reenterLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\tget");
			set();
		} finally {
			reenterLock.unlock();
			reenterLock.unlock();
		}
	}
	public void set() {
		reenterLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\tset");
		} finally {
			reenterLock.unlock();
		}
	}
	
}
/**
 * Description:
 *  可重入锁(也叫做递归锁)
 *  指的是同一线程外层函数获得锁后,内层敌对函数任然能获取该锁的代码
 *  在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 *
 *  也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块

 * @date 2019-04-12 23:36
 **/
public class ReenterLockDemo {

	public static void main(String[] args) throws Exception{
		Phone phone = new Phone();
		new Thread(() ->{
			try {
				phone.sendSms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "t1").start();
		new Thread(() ->{
			try {
				phone.sendSms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "t2").start();
		
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		Thread t3 = new Thread(phone,"t3");
		Thread t4 = new Thread(phone,"t4");
		t3.start();
		t4.start();
	}
}
