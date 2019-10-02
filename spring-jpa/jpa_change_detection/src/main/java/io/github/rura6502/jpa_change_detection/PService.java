package io.github.rura6502.jpa_change_detection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PService
 */
@Service
public class PService {

  
  @Autowired
  PRepository pRepo;

  @Transactional
  public void changeNameById(Long personId, String newName) {
    Person person = pRepo.findById(personId).get();
    this.changeName(person, newName);
  }

  private void changeName(Person person, String newName) {
    person.setName(newName);
  }
}