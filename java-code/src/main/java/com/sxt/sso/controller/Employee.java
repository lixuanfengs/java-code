package com.sxt.sso.controller;

public class Employee {
	
	private String name;
	private Integer age;
	private Double money;
	
	public Employee() {
		
	}
	public Employee(String name, Integer age, Double money) {
		super();
		this.name = name;
		this.age = age;
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", money=" + money + "]";
	}
	
}
