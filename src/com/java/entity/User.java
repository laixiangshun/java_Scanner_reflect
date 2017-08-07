package com.java.entity;

public class User {

	private int id;
	private String name;
	private String password;
	private int age;
	
	public User(){}
	public User(int id,String name,String password,int age){
		this.id=id;
		this.name=name;
		this.password=password;
		this.age=age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", age=" + age + "]";
	}
	public void add(){
		System.out.println("add");
	}
	public void addWithParameters(String name,String password){
		System.out.println("带参数方法："+name+","+password);
	}
}
