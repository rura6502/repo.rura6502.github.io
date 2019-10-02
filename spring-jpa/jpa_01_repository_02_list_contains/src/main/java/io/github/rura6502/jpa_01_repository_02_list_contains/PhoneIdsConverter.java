package io.github.rura6502.jpa_01_repository_02_list_contains;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PhoneIdsConverter
 */
@Converter
@RequiredArgsConstructor
public class PhoneIdsConverter implements AttributeConverter<Collection<Phone>, String> {

  @NonNull
  private PhoneRepository phoneRepository; 

  @Override
  public String convertToDatabaseColumn(Collection<Phone> attribute) {
    return attribute
                .stream()
                .map((phone) -> {
                  return phone.getId().toString();
                }).collect(Collectors.joining(","));
  }

  @Override
  public Collection<Phone> convertToEntityAttribute(String dbData) {
    List<Long> phoneIds
      = List.of(dbData.split(","))
                .stream()
                .map(str -> {
                  return Long.parseLong(str);
                }).collect(Collectors.toList());
    List<Phone> 
    
    return phoneIds;
  }

  

  
}