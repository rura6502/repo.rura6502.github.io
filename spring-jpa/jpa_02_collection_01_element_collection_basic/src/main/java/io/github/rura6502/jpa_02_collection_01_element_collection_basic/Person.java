package io.github.rura6502.jpa_02_collection_01_element_collection_basic;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Person
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

  @Id 
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "person_phones"
    , joinColumns = @JoinColumn(name = "person_id")
  )
  private Collection<Phone> phones;
}