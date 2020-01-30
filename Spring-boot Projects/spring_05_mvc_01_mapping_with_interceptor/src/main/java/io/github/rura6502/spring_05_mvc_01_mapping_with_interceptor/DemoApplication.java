package io.github.rura6502.spring_05_mvc_01_mapping_with_interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @RestController
  @RequestMapping("/test/*")
  public class TestController {

    @GetMapping("test1")
    public String test1() {
      return "I am test1";
    }
  }

  @Configuration
  class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    TestInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(testInterceptor)
      .addPathPatterns("/test*/**")
      
      ;
    }
    
  }

  @Configuration
  class TestInterceptor implements HandlerInterceptor { // can extends HandlerInterceptorAdapter

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
      long startTime = System.currentTimeMillis();
      request.setAttribute("startTime", startTime);
      System.out.println(startTime);
      return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
      long startTime = (Long) request.getAttribute("startTime");
      request.removeAttribute("startTime");

      long endTime = System.currentTimeMillis();
      // modelAndView.addObject("handlingTime", endTime - startTime);
      System.out.println(endTime - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
      
    }
  }
}
