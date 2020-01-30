package io.iochord.rnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configurable
public class ReturnTread implements Runnable {

  @Autowired
  SimpMessagingTemplate template;

  @Override
  public void run() {
    // TODO Auto-generated method stub

    for (int i = 0; i < 10; i++) {
      template.convertAndSend("/topic/img-test", "i am thread : " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

}
