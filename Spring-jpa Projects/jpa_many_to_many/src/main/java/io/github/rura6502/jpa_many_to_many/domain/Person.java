package io.github.rura6502.jpa_many_to_many.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Person {

  
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NonNull
  private String name;

  
  @OneToOne(cascade = CascadeType.ALL
                    , fetch = FetchType.EAGER)
  private Club mainClub;

  public Person(@NonNull String name, Club mainClub) {
    this.name = name;
    this.mainClub = mainClub;
  }

  
  
  
}