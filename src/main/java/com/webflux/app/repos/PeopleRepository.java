package com.webflux.app.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.webflux.app.entity.Person;

@Repository
public interface PeopleRepository extends ReactiveCrudRepository<Person, Long>{

}
