package io.github.rura6502.spring_09_exception_01_advice_layer_test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }
  
  @RestController
  class TestController {

    @Autowired TestService ts;

    @GetMapping("/test1")
    public String exInController() {
      throw new CustomException("this is exInController");
    }

    @GetMapping("/test2")
    public String exInService1() {
      ts.exTest1();
      return null;
    }

    @GetMapping("/test3")
    public String exInService2() {
      ts.exTest2();
      return null;
    }
  }

  @Service
  class TestService {

    @Autowired TestComponent tComponent;

    public void exTest1() {
      throw new CustomException("this is exTest in TestService");
    }
    public void exTest2() {
      tComponent.test();
    }
  }

  @Component
  class TestComponent {
    public void test() {
      throw new CustomException("tis is in component");
    }
  }

  @ControllerAdvice
  class ExHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> global(CustomException e, WebRequest request, HttpServletRequest sR) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}






class CustomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CustomException(String msg) {
    super(msg);
  }

}
