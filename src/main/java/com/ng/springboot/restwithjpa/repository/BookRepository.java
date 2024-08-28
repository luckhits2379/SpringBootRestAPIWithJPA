package com.ng.springboot.restwithjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ng.springboot.restwithjpa.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
