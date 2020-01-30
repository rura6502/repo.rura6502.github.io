package io.github.rura6502.jpa_one_to_many;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * One
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Many {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String value;

  private String desc;

  private long oneId;

}