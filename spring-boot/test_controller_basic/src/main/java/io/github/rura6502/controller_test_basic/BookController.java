package io.github.rura6502.controller_test_basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookController
 */
@RestController
public class BookController {

  @Autowired
  BookService bookSrv;

  @GetMapping("/books")
  public List<Book> getBooks() {
    return bookSrv.getBooks();
  }

  @GetMapping("/book/{bookId}")
  public Book getBook(@PathVariable("bookId") long bookId) {
    return bookSrv.getBook(bookId);
  }
  
  @PostMapping("/book")
  public Book createBook(@RequestBody Book book) {
    return bookSrv.createBook(book);
  }

  @DeleteMapping("/book/{bookId}")
  public void deleteBook(@PathVariable("bookId") long bookId) {
    bookSrv.deleteBookById(bookId);
  }

  
}