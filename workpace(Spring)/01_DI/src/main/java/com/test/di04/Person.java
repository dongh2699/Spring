package com.test.di04;

public class Person {

	private String name;
	private String gender;
	private String age;
	private String juminNo;
	
	public Person() {}
	
	public Person(String name,String gender,String age, String juminNo) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.juminNo = juminNo;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJuminNo() {
		return juminNo;
	}

	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}
	
	
	
}
