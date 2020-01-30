package io.github.rura6502.controller_test_basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Book
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;
  String author;
  int price;

  
}