package com.sxt.sso.juc;

public class SingletonDemo {
	
	public static volatile SingletonDemo instance = null;
	
	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
	}
	//单例模式DCL
	public static  SingletonDemo getSingletonDemo() {
		if(instance == null) {
			synchronized(SingletonDemo.class) {
				if(instance == null) {
					instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		//单线程main
		/*System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());
		System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());
		System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());*/
		
		for (int i = 0; i < 10; i++) {
			new Thread(()-> {
				
				SingletonDemo.getSingletonDemo();
				
				}, String.valueOf(i)).start();
		}
	}
	
}
