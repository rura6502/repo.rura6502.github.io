package io.github.rura6502.jpa_01_repository_03_in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  PersonARepository pARepo;

  @Autowired
  PersonBRepository pBRepo;

  @Bean
  CommandLineRunner runner() {
    return args -> {

      // System.out.println("############# test1 ");
      // PersonA aa = pARepo.save(new PersonA(0L, "woo", "BUSAN"));
      // PersonA ab = pARepo.save(new PersonA(0L, "kim", "SEOUL"));
      // PersonA ac = pARepo.save(new PersonA(0L, "lee", "ULSAN"));
      // System.out.println(pARepo.findAllByCityIn(new String[]{"BUSAN", "ULSAN"}));
      
      System.out.println();

      System.out.println("############# test1 ");
      PersonB ba = pBRepo.save(new PersonB(0L, "woo", List.of("BUSAN", "SEOUL")));
      PersonB bb = pBRepo.save(new PersonB(0L, "kim", List.of("SEOUL", "ULSAN")));
      PersonB bc = pBRepo.save(new PersonB(0L, "lee", List.of("ULSAN", "DAEGU")));
      PersonB bd = pBRepo.save(new PersonB(0L, "lee", List.of("ULSAN", "NORTH-DAEGU")));
      // System.out.println(ba);
      // System.out.println(pBRepo.findAllByCities("BUSAN"));
      System.out.println(pBRepo.findAllByCitiesIn(List.of("DAEGU")));
    };
  }
}
