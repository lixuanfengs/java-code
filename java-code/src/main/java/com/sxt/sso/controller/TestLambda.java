package com.sxt.sso.controller;

import org.junit.jupiter.api.Test;

public class TestLambda {
	
	@Test
	public void toupdateString() {
		
		//System.out.println(updateString("avb",e ->e.toUpperCase()));
		
		System.out.println(su(2,3,(x, y) -> x + y));
		
	}
	
	public String updateString(String string,StringTestInterface str) {
		return  str.getValue(string);
	}
	
	public Integer su(Integer s1,Integer s2,LambdaTest<Integer,Integer> la) {
		return la.tcc(s1,s2);
	}
}
