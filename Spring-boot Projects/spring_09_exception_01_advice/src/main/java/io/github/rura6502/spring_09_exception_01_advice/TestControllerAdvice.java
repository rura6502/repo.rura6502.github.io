package io.github.rura6502.spring_09_exception_01_advice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.rura6502.spring_09_exception_01_advice.DemoApplication.CustomException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * TestControllerAdvice
 */
@ControllerAdvice
@Slf4j
// @Component
public class TestControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<Object> global(CustomException e, WebRequest request, HttpServletRequest sR) {


    HttpStatus httpStatus = e.getHttpStatus() != null
                                                ? e.getHttpStatus() 
                                                : HttpStatus.INTERNAL_SERVER_ERROR;
    String path = (String)request.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping"
                                                            , RequestAttributes.SCOPE_REQUEST);
    LocalDateTime timestamp = LocalDateTime.now();
    String message = e.getMessage();
    Object description = e.getDescription();

    String reqBody = null;
    try {
      reqBody = new BufferedReader(new InputStreamReader(sR.getInputStream())).readLine();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      
    }

    CustomErrorAttribute cea
        = new CustomErrorAttribute(httpStatus
                                                      , path
                                                      , timestamp
                                                      , message
                                                      , reqBody
                                                      , description);

    return new ResponseEntity<Object>(cea, httpStatus);
  }
}

@Data
@AllArgsConstructor
class CustomErrorAttribute {
  HttpStatus httpStatus;
  String path;
  LocalDateTime timestamp;
  String message;
  String reqBody;
  Object description;
}