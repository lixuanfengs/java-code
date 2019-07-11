package com.sxt.sso.controller;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long start;
	private Long end;
	
	private static final long THRESHOLD = 10000;
	
	public ForkJoinCalculate(Long start,Long end) {
		this.start = start;
		this.end = end;
	}
	
	protected Long compute() {
		Long length = end - start;
		
		if(length >= THRESHOLD) {
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum +=i;
			}
			return sum;
		}else {
			
			long middle = (start + end) / 2;
			
			ForkJoinCalculate left =  new ForkJoinCalculate(start, middle);
			left.fork();
			
			ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
			right.fork();
			
			return left.join() + right.join();
			
		}
		
	}

}
