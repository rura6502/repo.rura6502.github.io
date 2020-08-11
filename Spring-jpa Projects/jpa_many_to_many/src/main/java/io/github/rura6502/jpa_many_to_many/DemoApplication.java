package io.github.rura6502.jpa_many_to_many;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rura6502.jpa_many_to_many.domain.Club;
import io.github.rura6502.jpa_many_to_many.domain.Person;
import io.github.rura6502.jpa_many_to_many.repo.ClubRepository;
import io.github.rura6502.jpa_many_to_many.repo.ClubRepository;
import io.github.rura6502.jpa_many_to_many.repo.PersonRepository;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  ClubRepository cRepo;

  @Autowired
  PersonRepository pRepo;

  @Bean
  @Transactional
  CommandLineRunner runner() {
    return args -> {

      Club c1 = cRepo.save(new Club("노래"));
      Club c2 = cRepo.save(new Club("음악"));
      // Club c3 = cRepo.save(new Club("꽃꽃이"));
      // Club c4 = cRepo.save(new Club("과일"));

      Person p1 = pRepo.save(new Person("김길동", new Club("김길동 기본클럽")));
      Person p2 = pRepo.save(new Person("강민성", new Club("강민성 기본클럽")));
      Person p3 = pRepo.save(new Person("우종성", new Club("우종성 기본클럽")));
      Person p4 = pRepo.save(new Person("유관순", new Club("유관순 기본클럽")));

      c1.setPersons(List.of(p1, p2, p3));
      c2.setPersons(List.of(p4));
      
      cRepo.save(c1);
      cRepo.save(c2);
    };
  }

}

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
class ClubController {

  private final ClubRepository gRepo;

  @GetMapping("/")
  public Collection<Club> getClubs() {
    return gRepo.findAll();
  }

  @GetMapping("/{id}")
  public Club getClubById(@PathVariable("id") long id) {
    return gRepo.findById(id).get();
  }

  @DeleteMapping("/{id}")
  public void deleteClubById(@PathVariable("id") long id) {
    gRepo.deleteById(id);
  }
}

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
class PersonController {

  private final PersonRepository pRepo;

  @GetMapping("/")
  public Collection<Person> getPersons() {
    return pRepo.findAll();
  }

  @GetMapping("/{id}")
  public Person getPersonById(@PathVariable("id") long id) {
    return pRepo.findById(id).get();
  }

  @DeleteMapping("/{id}")
  public void deletePersonById(@PathVariable("id") long id) {
    pRepo.deleteById(id);
  }
}