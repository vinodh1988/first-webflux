package com.webflux.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.app.entity.Person;
import com.webflux.app.services.DataService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FirstController {
  @Autowired
  private DataService data;
	
	@GetMapping("/greet")
	public Mono<String> getInfo(){
		return data.getData();
	}
	
	@GetMapping("/flux-people")
	public Flux<Person> getPeople(){
		return data.getPeople();
	}
	
	
	@GetMapping("/people")
	public Flux<Person> getPeople2(){
		return data.getPeopleData();
	}
	
	@PostMapping("/people")
	public Mono<Person> addPErson(@RequestBody Mono<Person> p){
		return p.flatMap(data::addPerson);
	}
	
}
