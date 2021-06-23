package com.webflux.app.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.app.entity.Person;

public interface PeopleRepository extends ReactiveCrudRepository<Person, Long>{

}
