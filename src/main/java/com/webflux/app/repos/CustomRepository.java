package com.webflux.app.repos;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.webflux.app.entity.Author;
import com.webflux.app.entity.Author.AuthorBuilder;
import com.webflux.app.entity.Book;
import com.webflux.app.entity.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomRepository  {
@Autowired
	DatabaseClient db;

public Mono<Person> addPerson(Person person){
	   
return db.sql("insert into person(name,city) values(:name,:city)")
.bind("name", person.getName())
.bind("city", person.getCity())
   .fetch()
   .rowsUpdated()
   .map(x->x==1?person:null);
 
}

public Flux<Person> getPerson() {
	// TODO Auto-generated method stub

	return db.sql("select * from person")
	.map(row->Person
	.builder()
	.name(row.get("name",String.class))
	.sno(row.get("sno",Integer.class))
	.city(row.get("city",String.class))
	.build()
	).all();
}

public Flux<Author> getAuthors() {
	// TODO Auto-generated method stub
	      System.out.println("This is running");
	       return db.sql("select author.authorid,book.name as bookname,author.name as authorname,bookid,price from author join book on author.authorid=book.authorid order by author.authorid")
	       .fetch()
	            .all()
	            .bufferUntilChanged( result -> result.get("authorid"))
	            .map(list -> {
	                      AuthorBuilder author=  Author.builder();
	                       author.authorid((Integer)list.get(0).get("authorid"));
	                       author.name((String)list.get(0).get("authorname"));
	                       author.books(list.stream().map(
	                        x->{
	                        return Book.builder()
	                        .bookid((Integer)x.get("bookid"))
	                        .name((String)x.get("bookname"))
	                        .price((Double)x.get("price"))
	                        .build();
	                       }).collect(Collectors.toSet()));
	                       
	                    return   author.build();
	                           
	                           
	                          }
	                       );
	         
	}
}
