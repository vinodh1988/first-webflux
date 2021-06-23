package com.webflux.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webflux.app.entity.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataService {

	  public Mono<String> getData(){
		  return Mono.just("My First Webflux API");
	  }
	  
	  public Flux<Person> getPeople(){
		  List<Person> l=new ArrayList<Person>();
		  l.add(new Person(1,"Roger","Chennai"));
		  l.add(new Person(2,"Harry","Mumbai"));
		  l.add(new Person(3,"Vijay","Hyderabad"));
		  l.add(new Person(4,"Tony","Jaipur"));
		  l.add(new Person(5,"Kasi","Bhopal"));
		  return Flux.fromIterable(l);
	  }
}
