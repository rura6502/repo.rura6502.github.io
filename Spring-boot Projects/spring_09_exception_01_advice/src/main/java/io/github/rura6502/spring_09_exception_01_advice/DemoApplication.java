package io.github.rura6502.spring_09_exception_01_advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication

@Slf4j
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  

  @RestController
  @RequestMapping("/test1")
  class TestController {

    @GetMapping("/t1/{flag}")
    @PostMapping("/t1/{flag}")
    @RequestMapping(path = "/t1/{flag}", method = {RequestMethod.POST, RequestMethod.GET})
    public String t1(@PathVariable("flag") String flag) throws Exception {
      if (flag.equals("ex"))
        throw new Exception();
      else if (flag.equals("run"))
        throw new RuntimeException();
      else if (flag.equals("404"))
        throw new CustomException(HttpStatus.NOT_FOUND, "404");
      else if (flag.equals("400"))
        throw new CustomException(HttpStatus.BAD_REQUEST, "400");
      else if (flag.equals("cy"))
        throw new TestException("room not found *****");
      else
        return "Hello, I am t1 : " + flag;
    }
  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  class TestException extends RuntimeException {

    String testMessage = "this is exception test message";

    public TestException(String message) {
      super(message);
    }
  }


  
  @ResponseStatus(code = HttpStatus.NOT_FOUND) // @ControllerAdviser 에서는 동작하지 않음.
  class CustomException extends RuntimeException {

    @Setter @Getter
    private HttpStatus httpStatus;

    @Setter @Getter
    private Object description;


    public CustomException(HttpStatus httpStatus, String message) {
      this(httpStatus, message, null);
    }
    public CustomException(HttpStatus httpStatus, String message, Object description) {
      super(message);
      this.setHttpStatus(httpStatus);
      this.setDescription(description);
    }
    public CustomException(String message) {
      this(null, message, null);
    }
  }
}
