package io.github.rura6502.java_properties_using_db;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Properties
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Properties {

  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String key;

  @NonNull
  private String value;

  public void setKey(Key key) {
    this.key = key.name();
  }

  public Key getKey() {
    return Key.valueOf(this.key);
  }

  public Properties(Key key, String value) {
    this.key = key.name();
    this.value = value;
  }
  
}