package io.github.rura6502.jpa_casecade_basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Test2
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test2 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String a2;
  String b2;
}