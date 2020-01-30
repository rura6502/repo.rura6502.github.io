package io.github.rura6502.spring_02_properties_01_get_properties;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import lombok.Data;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  Test1Properties test1Properties;

  @Autowired
  Test2Properties test2Properties;

  @Autowired
  Test3Properties test3Properties;

  @Value("${application_properties_key1}")
  String application_properties_key1;
  

  @Bean
  CommandLineRunner runner() {
    return args -> {
      System.out.println("\n");
      System.out.println("##### application.properties, @Value");
      System.out.println(application_properties_key1);
      System.out.println("\n");

      System.out.println("##### test1.properties, @PropertySource, @Value");
      System.out.println(test1Properties.getThis_is_key1());
      System.out.println("\n");

      System.out.println("##### test2.properties, @PropertySource, @ConfigurationProperties(prefix), variable Naming Mapping");
      System.out.println(test2Properties.getKey());
      System.out.println("-- array");
      System.out.println(test2Properties.getArrKey());
      System.out.println("-- map");
      System.out.println(test2Properties.getMapKey());
      System.out.println("-- class");
      System.out.println(test2Properties.getTmp());
      System.out.println("-- time");
      System.out.println(test2Properties.getSecond());
      System.out.println("-- nameing Convention");
      System.out.println(test2Properties.getAaaBbb());
      
      System.out.println("\n");

      System.out.println("##### test2.properties, @PropertySources");
      System.out.println(test3Properties.getKey1());
      System.out.println(test3Properties.getKey2());
    };
  }
}


@Data @Configuration
@PropertySource("classpath:test1.properties")
class Test1Properties {
  @Value("${test1Key1}") String this_is_key1;
}

@Data @Configuration
@PropertySource("classpath:test2.properties")
@ConfigurationProperties(prefix = "test2")
class Test2Properties {
  String key;
  List<Integer> arrKey;
  Map<String, String> mapKey;
  TmpObject tmp;
  @DurationUnit(ChronoUnit.SECONDS)
  Duration second;
  String aaaBbb;
}

@Data
class TmpObject {
  String val1;
  String val2;
}

@Data @Configuration
@PropertySources({
  @PropertySource("classpath:test2.properties")
  , @PropertySource("classpath:test2.properties")
})
class Test3Properties {
  @Value("test3Key1")
  String key1;
  @Value("test3Key2")
  String key2;
}
