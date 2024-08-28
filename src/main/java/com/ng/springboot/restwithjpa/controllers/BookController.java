package com.ng.springboot.restwithjpa.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ng.springboot.restwithjpa.entity.Book;
import com.ng.springboot.restwithjpa.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping(value = "/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(required = true) String id) {

		Optional<Book> optionalBook = bookService.getBookById(Integer.parseInt(id));

		if (optionalBook.isEmpty()) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalBook.get());

	}

	@GetMapping(value = "/books/")
	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> BookList = (List<Book>) bookService.getAllBooks();

		if (BookList.isEmpty()) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(BookList);

	}

	@PostMapping(value = "/books/")
	public ResponseEntity<Book> createBook(@RequestBody Book book) throws URISyntaxException {

		Book createdBook = bookService.createNewBook(book);

		return ResponseEntity.created(new URI("/books/" + createdBook.getId())).build();

	}

	@PutMapping(value = "/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable(required = true) String id) {

		Optional<Book> optional = bookService.updateBook(Integer.parseInt(id), book);

		if (optional.isEmpty()) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.accepted().body(optional.get());

	}

	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable(required = true) String id) {

		bookService.deleteBook(Integer.parseInt(id));

		return ResponseEntity.ok().build();

	}

}
