package io.github.rura6502.jpa_change_detection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;
}
