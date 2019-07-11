package com.sxt.sso.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TestStreamAPI {
	
	/**
	 * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？，给定【1，2，3，4，5】
	 * 应该返回【1，4，9，16，25】
	 */
	@Test
	public void test1() {
		Integer[] nums = new Integer[] {1,2,3,4,5};
		
		Arrays.stream(nums)
			  .map((x) -> x * x)
			  .forEach(System.out::println);
		
	}
	List<Employee> employees = Arrays.asList(new Employee("张三",12,9998.0),
			new Employee("李四",19,9998.0),
			new Employee("王五",10,9998.0),
			new Employee("赵柳",20,9998.0),
			new Employee("天气",30,9998.0),
			new Employee("青苔",40,9998.0));
	
	//用 map 和 reduce 方法计算流中有多少个Employee?
	@Test
	public void test2() {
		Optional<Integer> sums = employees.stream()
				 .map((e) -> 1)
				 .reduce(Integer::sum);
		System.out.println(sums.get());
	}
}
