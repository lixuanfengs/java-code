package com.sxt.sso.controller;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Stream1 { 

	List<Employee> employees = Arrays.asList(new Employee("张三",12,9998.0),
			new Employee("李四",19,9998.0),
			new Employee("王五",10,9998.0),
			new Employee("赵柳",20,9998.0),
			new Employee("天气",30,9998.0),
			new Employee("青苔",40,9998.0));
	
	
	@Test
	public void testLambda() {
		
		Stream<Employee> sx = employees.stream()
		.filter((e) -> e.getAge()>19);
		
		System.out.println("-----------------------------");
		
		Optional<Employee> sx1 = employees.parallelStream()
				.filter((e) -> e.getAge() > 19).findAny();
		System.out.println(sx1.get());
		
		System.out.println("-----------------------------");
		//max min
		Optional<Employee> s = employees.stream().max((e3,e2) -> Double.compare(e3.getAge(), e2.getAge()));
		System.out.println(s.get());
		
		System.out.println("-----------------------------");
		
		//reduce
		Optional<Double> sum = employees.stream()
				 .map((es) -> es.getMoney())
				 .reduce(Double::sum);
		System.out.println(sum.get());
		
		System.out.println("-----------------------------");
		
		
		
		Map<Integer, String> map = employees.stream().collect(Collectors.toMap(Employee::getAge , Employee::getName));
		
		map.keySet().forEach((key) -> System.out.println(map.get(key) + ".."));
		
/*		employees.stream().collect(Collectors.to);
*/	}
	
}
 