package io.github.rura6502.java_properties_using_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * PropertiesInit
 */
@Configuration
public class PropertiesInit implements CommandLineRunner {

  @Autowired
  private PropertiesRepository pRepo;
  @Override
  public void run(String... args) throws Exception {
    pRepo.save(new Properties(Key.prop_aaa_bbb, "bbb"));
    pRepo.save(new Properties(Key.prop_aaa_ccc, "ccc"));
    pRepo.save(new Properties(Key.prop_aaa_ddd, "ddd"));
    pRepo.save(new Properties(Key.prop_aaa_eee, "eee"));
  }

  
}