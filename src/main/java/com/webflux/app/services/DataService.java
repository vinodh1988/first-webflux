package com.webflux.app.services;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DataService {

	  public Mono<String> getData(){
		  return Mono.just("My First Webflux API");
	  }
}
