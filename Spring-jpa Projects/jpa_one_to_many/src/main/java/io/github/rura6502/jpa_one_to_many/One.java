package io.github.rura6502.jpa_one_to_many;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * One
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class One {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NonNull
  private long id;

  @NonNull
  private String value;

  @OneToMany(mappedBy = "oneId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // @JoinColumn(name = "oneId")
  private List<Many> manys;
}