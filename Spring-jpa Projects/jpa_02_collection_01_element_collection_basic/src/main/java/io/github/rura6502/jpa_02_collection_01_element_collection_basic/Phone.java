package io.github.rura6502.jpa_02_collection_01_element_collection_basic;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Phone
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Phone {

  String company;
  String number;
}