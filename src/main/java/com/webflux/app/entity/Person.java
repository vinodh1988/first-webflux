package com.webflux.app.entity;

import org.springframework.data.annotation.Id;

public class Person {
   //Person(1,"vinodh","mumbai") -->Update
   //Person("Raj","Chennai") --> insert
	@Id
   private Integer sno;
   private String name;
   private String city;
   
public Person() {}
   
public Person(int sno, String name, String city) {
	super();
	this.sno = sno;
	this.name = name;
	this.city = city;
}
public Integer getSno() {
	return sno;
}
public void setSno(Integer sno) {
	this.sno = sno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}

@Override
public String toString() {
	return "Person [sno=" + sno + ", name=" + name + ", city=" + city + "]";
}
   
   
   
}
