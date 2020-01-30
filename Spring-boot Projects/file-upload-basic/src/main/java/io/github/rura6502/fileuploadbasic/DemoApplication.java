package io.github.rura6502.fileuploadbasic;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}

@RestController
class TestController {

  @PostMapping("/test")
  public void test(
    Test test1  
    , @RequestParam("file") MultipartFile file
  )
      throws IllegalStateException, IOException {
        System.out.println(test1.a);
        System.out.println(test1.b);
        System.out.println(test1.c);
      File dest = new File("C:/Users/abcd/Desktop/"
      + LocalTime.now().getMinute()
      + "_"
      + LocalTime.now().getSecond());

      file.transferTo(dest);
    
  }
}

@Data 
class Test2{
  // MultipartFile file;
  String a;
  String b;
  String c;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Test{
  String a;
  String b;
  String c;
}