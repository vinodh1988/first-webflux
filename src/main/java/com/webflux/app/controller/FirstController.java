package com.webflux.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.app.entity.Author;
import com.webflux.app.entity.Person;
import com.webflux.app.messaging.MessageSender;
import com.webflux.app.services.DataService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;


@RestController
@RequestMapping("/api")
public class FirstController {
  @Autowired
  private DataService data;
  
  @Autowired
	  public MessageSender sender;
	
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
	
	@GetMapping("/authors")
	public Flux<Author> getAuthors(){
		
		return data.getAuthorData();
	}
	
	
	@PostMapping("/people")
	public Mono<Person> addPerson(@RequestBody Mono<Person> p){
		 return p.flatMap(data::addPerson).doOnNext(
				   x->{
					  sender.SendMessage(x);
				   }
				 );
		
	
	}
	
	@PostMapping("/author")
	public Mono<Void> addAuthor(@RequestBody Mono<Author> p){
		 return p.flatMap(data::addAuthor);
	}
	
	@PostMapping("/people2")
	public Mono<Person> addPerson2(@RequestBody Mono<Person> p){
		 return p.flatMap(data::addPerson).doOnNext(
				   x->{
					  sender.SendMessage(x);
				   }
				 );
		
	
	}
	
	@GetMapping("/people-combine")
	public Flux<Tuple3<Person,Person,Person>> groupThem(){
		//return Flux.merge(data.getPeopleData(),data.getPeople(),data.getPeople2());
		//return Flux.concat(data.getPeopleData(),data.getPeople(),data.getPeople2());
		return Flux.zip(data.getPeopleData(),data.getPeople(),data.getPeople2());
	}
	
}
