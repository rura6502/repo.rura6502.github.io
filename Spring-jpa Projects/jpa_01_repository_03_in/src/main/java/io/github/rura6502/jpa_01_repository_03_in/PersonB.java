package io.github.rura6502.jpa_01_repository_03_in;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PersonB
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonB {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  // @Convert(converter = CitiesConverter.class)
  @ElementCollection(fetch = FetchType.EAGER)
  private Collection<String> cities;
}