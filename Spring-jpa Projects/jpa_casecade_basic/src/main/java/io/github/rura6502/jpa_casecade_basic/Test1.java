package io.github.rura6502.jpa_casecade_basic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Test1
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test1 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String a1;
  String b1;

  @OneToOne(cascade = CascadeType.ALL)
  Test2 test2;
}