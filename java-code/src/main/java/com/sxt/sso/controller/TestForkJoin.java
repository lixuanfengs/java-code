package com.sxt.sso.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.jupiter.api.Test;

public class TestForkJoin {

	@Test
	public void test() {
		
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate((long) 0, 100000000L);
		Long sum = pool.invoke(task);
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		System.out.println("耗费时间为："+ Duration.between(start, end).toMillis());
	}
	
}
