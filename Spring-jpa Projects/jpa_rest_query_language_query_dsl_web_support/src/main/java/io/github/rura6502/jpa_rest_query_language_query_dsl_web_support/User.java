package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String firstName;
  String lastName;
  @Enumerated(EnumType.STRING)
  Gender gender;
  @Enumerated(EnumType.STRING)
  City city;
  String email;
}