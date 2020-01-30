package io.iochord.rnd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsocketWithImgApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(WebsocketWithImgApplication.class, args);
  }

  @Override
  public void run(String... arg0) throws Exception {
    // TODO Auto-generated method stub

    // File files = new ClassPathResource("test_image").getFile();
    // File file = files.listFiles()[0];
    //
    // FileInputStream fis = new FileInputStream(file);
    // byte[] bytes = new byte[(int) file.length()];
    // fis.read(bytes);
    // System.out.println(new String(Base64.encodeBase64(bytes), "UTF-8"));
    // for (String str : file.list()) {
    // System.out.println(new String(Base64.encodeBase64(binaryData)));
    // }

    // data:image/jpeg;base64,

  }
}
