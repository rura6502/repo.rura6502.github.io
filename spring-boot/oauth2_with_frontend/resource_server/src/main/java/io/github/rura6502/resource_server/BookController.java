package io.github.rura6502.resource_server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookController
 */
@RestController
@RequestMapping("/api")
public class BookController {

  @Autowired
  BookRepository br;

  @GetMapping("/books")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
  @CrossOrigin
  public List<Book> getAll() {
    return br.findAll();
  }

  
}