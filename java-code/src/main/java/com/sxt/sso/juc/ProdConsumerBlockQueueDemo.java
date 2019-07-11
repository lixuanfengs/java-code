package com.sxt.sso.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
	/**
	 * 默认开启 进行生产者和消费者的交互
	 */
	private volatile boolean FLAG = true;
	/**
	 * 默认是 0
	 */
	private AtomicInteger atomicInteger = new AtomicInteger();
	/**
	 * 阻塞队列
	 */
	private BlockingQueue<String> blockingQueue = null;
	
	public MyResource(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
		System.out.println(blockingQueue.getClass().getName());
	}
	
	/**
	 * 生产者
	 * @throws InterruptedException 
	 */
	public void  myProd() throws Exception  {
		String data = null;
		boolean returnValue;
		while (FLAG) {
			data = atomicInteger.incrementAndGet() + "";
			returnValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
			if(returnValue) {
				System.out.println(Thread.currentThread().getName() +"\t 插入队列数据"+data+"成功" );
			}else {
				System.out.println(Thread.currentThread().getName() +"\t 插入队列数据"+data+"失败" );
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName() + "\t 停止表示 flag ");
	}
	/**
	 * 消费者
	 * @throws Exception
	 */
	public void myConsumer() throws Exception {
		String result = null;
		while (FLAG) {
			result = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if(result==null || result.equalsIgnoreCase("")) {
				FLAG = false;
				System.out.println(Thread.currentThread().getName()+ "\t 超过2秒钟没有收到数据，消费者退出");
				System.out.println();
				System.out.println();
				System.out.println();
				return;
			}
			System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");
		}
	}
	
	public void stop() {
		FLAG = false;
	}
}
/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 * @author Administrator
 *
 */
public class ProdConsumerBlockQueueDemo {
	
	public static void main(String[] args) {
		MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
		
		new Thread(() -> {
			try {
				myResource.myProd();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Prod").start();

		new Thread(() -> {
			try {
				myResource.myConsumer();;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Consumer").start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("时间到,停止活动");
        myResource.stop();

	}

}
