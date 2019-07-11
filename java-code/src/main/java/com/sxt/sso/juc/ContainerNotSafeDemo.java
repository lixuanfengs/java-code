package com.sxt.sso.juc;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合不安全的问题
 * @author Administrator
 *
 */
public class ContainerNotSafeDemo {
	
	public static void main(String[] args) {
		
		//List<String> list = new ArrayList<>();
		//List<String> list = Collections.synchronizedList(new ArrayList<>());
		List<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
		
	}

}
