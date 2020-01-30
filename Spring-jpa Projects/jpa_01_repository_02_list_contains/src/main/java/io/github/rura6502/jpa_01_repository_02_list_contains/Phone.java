package io.github.rura6502.jpa_01_repository_02_list_contains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Phone
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String number;
}