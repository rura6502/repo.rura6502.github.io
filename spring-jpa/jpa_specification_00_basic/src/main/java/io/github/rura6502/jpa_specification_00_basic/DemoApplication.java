package io.github.rura6502.jpa_specification_00_basic;

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
  private ObjRepository objRepo;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      Obj obj1 = objRepo.save(new Obj(0L, "aa", "bb", "cc"));
      Obj obj2 = objRepo.save(new Obj(0L, "dd", "ee", "ff"));
      Obj obj3 = objRepo.save(new Obj(0L, "gg", "hh", "ii"));
      Obj obj4 = objRepo.save(new Obj(0L, "jj", "kk", "ll"));
      Obj obj5 = objRepo.save(new Obj(0L, "mm", "nn", "oo"));
      Obj obj6 = objRepo.save(new Obj(0L, "pp", "qq", "rr"));
    };
  }

}
