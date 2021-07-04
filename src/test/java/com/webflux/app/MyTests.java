package com.webflux.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.webflux.app.entity.Person;
import com.webflux.app.repos.PeopleRepository;
import com.webflux.app.services.DataService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@ExtendWith( SpringExtension.class )
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MyTests{
   
	@Autowired
	DataService service;
	

	@MockBean
	  PeopleRepository repository;
	
	 WebClient webclient=WebClient.builder()
			 .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
             .baseUrl("http://localhost:8880").build();
	
	@Test
	public void testPeople() {
		Flux<Person> verify=service.getPeople();
		StepVerifier.create(verify)
		.expectNextCount(5)
		.verifyComplete();
	}
	
	/*@Test
	public void testPeople2() {
		Flux<Person> verify=service.getPeopleData();
		StepVerifier.create(verify)
		.expectNextCount(20)
		.verifyComplete();
	}*/
	
	@Test
	public void testReadFlow() {
		Person p = new Person();
		p.setSno(15);
		p.setName("Vinodh");
		p.setCity("Chennai");
		
		ArrayList<Person> list=new ArrayList<Person>();
		list.add(p);
		list.add(new Person(23,"Jay","Mumbai"));
		Flux<Person> peopleFlux = Flux.fromIterable(list);
        Mockito
        .when(repository.findAll())
        .thenReturn(peopleFlux);
        
        Flux<Object> responseMono = this.webclient
                .get()
                .uri("/api/people")
                .exchangeToFlux(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));
        
         StepVerifier.create(responseMono)
         .expectNextCount(2)
         .verifyComplete();
         
         Mockito.verify(repository, times(1)).findAll();
	}

	@Test
	public void postTest(){
		Person p = new Person();
		
		p.setName("Vinodh");
		p.setCity("Chennai");
		Mockito.when(repository.save( any(Person.class))).thenReturn(Mono.just(p));
		
		Mono<Person> responseMono = this.webclient
                .post()
                .uri("/api/people")
                .body(Mono.just(p),Person.class)
                .retrieve()
                .bodyToMono(Person.class);
          

	
        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
        
    	Mockito.verify(repository, times(1)).save(any(Person.class));
	}
		
		
	
	
	  
	private Flux<Object> exchange(ClientResponse cr){
	        if(cr.rawStatusCode() == 500)
	            return cr.bodyToFlux(Exception.class);
	        else
	            return cr.bodyToFlux(Person.class);
	    }
	  
	  
}


