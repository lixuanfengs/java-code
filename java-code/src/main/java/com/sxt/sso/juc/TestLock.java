package com.sxt.sso.juc;

import java.util.HashMap;

/**
 * 验证 HashMap 不是线程安全的
 * @author Administrator
 *
 */
public class TestLock {
    public static void main(String[] args) throws InterruptedException {
    	for (int i = 0; i < 10; i++) {
    	  	final HashMap<String, String> firstHashMap = new HashMap<String, String>();
        	Thread t1=new Thread(){
        	    public void run() {
        	        for(int i=0;i<25;i++){
        	            firstHashMap.put(String.valueOf(i), String.valueOf(i));
        	        }
        	    }
        	};
        	 
        	Thread t2=new Thread(){
        	    public void run() {
        	        for(int j=25;j<50;j++){
        	            firstHashMap.put(String.valueOf(j), String.valueOf(j));
        	        }
        	    }
        	};
        	 
        	t1.start();
        	t2.start();
        	//主线程休眠1秒钟，以便t1和t2两个线程将firstHashMap填装完毕。
        	Thread.sleep(1000);
        	for(int l=0;l<50;l++){
        	    //如果key和value不同，说明在两个线程put的过程中出现异常。
        	    if(!String.valueOf(l).equals(firstHashMap.get(String.valueOf(l)))){
        	        System.err.println(String.valueOf(l)+":"+firstHashMap.get(String.valueOf(l)));
        	    }
        	}
		}
    }
}