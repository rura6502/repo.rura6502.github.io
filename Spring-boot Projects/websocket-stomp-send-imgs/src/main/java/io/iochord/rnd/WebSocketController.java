package io.iochord.rnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

  @Autowired
  SimpMessagingTemplate template;

  @GetMapping("/go")
  public void base64() throws InterruptedException, IOException {
    System.out.println("go rt");
    // for (int i = 0; i < 10; i++) {
    // Thread.sleep(500);
    // template.convertAndSend("/topic/img-test", "asdfasdfasdf");
    //
    // }

    File files = new ClassPathResource("test_image").getFile();
    for (File file : files.listFiles()) {
      System.out.println(file.getAbsolutePath());
      FileInputStream fis = new FileInputStream(file);
      byte[] bytes = new byte[(int) file.length()];
      fis.read(bytes);
      template.convertAndSend("/topic/img-test",
          "data:image/jpeg;base64," + new String(Base64.encodeBase64(bytes), "UTF-8"));
      fis.close();
      Thread.sleep(200);
    }

  }

}
