package io.github.rura6502.controller_test_basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookService
 */
@Service
public class BookService {

  @Autowired
  BookRepository bookRepo;

  public List<Book> getBooks() {
    return bookRepo.findAll();
  }

  public Book getBook(long bookId) {
    return bookRepo.findById(bookId).get();
  }
  
  public Book createBook(Book book) {
    return bookRepo.save(book);
  }

  public void deleteBookById(long bookId) {
    bookRepo.deleteById(bookId);
  }
}