package com.webflux.app.messaging;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webflux.app.entity.Person;

public class PersonSerializer implements Serializer<Person>{

	@Override
	public byte[] serialize(String topic, Person data) {
		// TODO Auto-generated method stub
		ObjectMapper obj=new ObjectMapper();
		  try {
			  byte[]   barray = obj.writeValueAsString(data).getBytes();
			  return barray;
			   } catch (Exception e) {
			     e.printStackTrace();
			     return new byte[0];
			   }
			   
	}

}
