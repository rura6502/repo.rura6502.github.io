package io.github.rura6502.java_properties_using_db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DBPropertiesManager
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DBPropertiesManager {

  private Map<Key, String> propertiesMap = new HashMap<>();

  @NonNull
  private final PropertiesRepository pRepo;

  public String get(Key key) {
    return propertiesMap.get(key);
  }

  public Map<Key, String> get() {
    return propertiesMap;
  }

  public void update(Properties prop) {
    propertiesMap.put(prop.getKey(), prop.getValue());
    this.updateToRepo(prop.getKey(), prop.getValue());
  }

  public Map<Key, String> update(List<Properties> propList) {
    List<Properties> newPropList = pRepo.saveAll(propList);
    init();
    return this.get();
  }

  @Scheduled(fixedDelay = 10000, initialDelay = 5000)
  public void init() {
    List<Properties> dbPropList = this.getAllFromRepo();
    dbPropList.parallelStream().forEach(prop -> {
      propertiesMap.put(prop.getKey(), prop.getValue());
    });
    log.info("Properties refresh");
  }

  private String getFromRepo(Key key) {
    return pRepo.findById(key).get().getValue();
  }

  private List<Properties> getAllFromRepo() {
    return pRepo.findAll();
  }

  private void updateToRepo(Key key, String value) {
    pRepo.save(new Properties(key, value));
  }
}