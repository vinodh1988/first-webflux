package com.webflux.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.app.entity.Person;
import com.webflux.app.repos.PeopleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataService {
	
	@Autowired
	  public  PeopleRepository repo;

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
	  
	  public Flux<Person> getPeopleData(){
		  return repo.findAll();
	  }
	  
	  public Mono<Person> addPerson(Person p){
		 return repo.save(p);
		  
	  }
}
