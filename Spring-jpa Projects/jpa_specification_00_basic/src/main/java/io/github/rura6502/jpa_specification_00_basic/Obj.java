package io.github.rura6502.jpa_specification_00_basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Obj
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Obj {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String a;
  String b;
  String c;


}