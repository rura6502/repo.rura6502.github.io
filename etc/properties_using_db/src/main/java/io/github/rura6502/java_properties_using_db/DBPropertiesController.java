package io.github.rura6502.java_properties_using_db;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * DBPropertiesController
 */
@RestController
public class DBPropertiesController {

  @Autowired
  private DBPropertiesManager dbPropManager;

  @GetMapping("/props")
  public Map<Key, String> getProps() {
    return dbPropManager.get();
  }

  @GetMapping("/props/{key}")
  public String getProps(@PathVariable(required = false) Key key) {
    return dbPropManager.get(key);
  }

  @PostMapping("/props")
  public Map<Key, String> updateProp(@RequestBody Map<String, String> propUpdation) {
    List<Properties> propList = new CopyOnWriteArrayList<>();
    propUpdation.keySet().parallelStream().forEach(key -> {
      propList.add(new Properties(Key.valueOf(key), propUpdation.get(key)));
    });
    return dbPropManager.update(propList);

  }
  
}

@Controller
class ViewController {
  @GetMapping("/")
  public String index() {
    return "index";
  }
}