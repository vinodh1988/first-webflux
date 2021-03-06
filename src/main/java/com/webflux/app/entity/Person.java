package com.webflux.app.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
   //Person(1,"vinodh","mumbai") -->Update
   //Person("Raj","Chennai") --> insert
	@Id
   private Integer sno;
   private String name;
   private String city;
  
   
}
