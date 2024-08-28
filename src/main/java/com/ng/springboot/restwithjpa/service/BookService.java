package com.ng.springboot.restwithjpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.springboot.restwithjpa.entity.Book;
import com.ng.springboot.restwithjpa.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Optional<Book> getBookById(int id) {

		return bookRepository.findById(id);

	}

	public Iterable<Book> getAllBooks() {

		return bookRepository.findAll();

	}

	public Book createNewBook(Book book) {

		return bookRepository.save(book);
	}

	public Optional<Book> updateBook(int id, Book book) {

		Optional<Book> optional = bookRepository.findById(id);

		if (optional.isPresent()) {

			Book updatedBook = optional.get();

			if (book.getTitle() != null) {
				updatedBook.setTitle(book.getTitle());
			}

			if (book.getAuthor() != null) {
				updatedBook.setAuthor(book.getAuthor());

			}

			return Optional.of(bookRepository.save(updatedBook));

		}

		return Optional.empty();
	}

	public void deleteBook(int id) {

		bookRepository.deleteById(id);
	}

}
