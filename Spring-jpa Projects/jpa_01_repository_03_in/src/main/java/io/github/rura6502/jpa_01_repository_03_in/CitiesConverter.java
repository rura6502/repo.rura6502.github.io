package io.github.rura6502.jpa_01_repository_03_in;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * CitiesConverter
 */
@Converter
public class CitiesConverter implements AttributeConverter<Collection<String>, String> {

  @Override
  public String convertToDatabaseColumn(Collection<String> attribute) {
    return attribute.stream().collect(Collectors.joining(","));
  }

  @Override
  public Collection<String> convertToEntityAttribute(String dbData) {
    return List.of(dbData.split(","));
  }
}