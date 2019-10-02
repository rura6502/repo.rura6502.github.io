package io.iochord.dev.ouath2_with_front_end;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Controller
  class TestController {
    @GetMapping("/")
    // @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView index(ModelAndView modelAndView, Principal principal) {
      modelAndView.setViewName("index");
      modelAndView.addObject("principal", principal);
      return modelAndView;
    }

    // @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test")
    @PreAuthorize("hasScope('write') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody Map<String, Object> index(Principal principal, Credential credential) {
      Map<String, Object> result = new HashMap<>();
      result.put("principal", principal);
      result.put("credential", credential);
      return result;
    }

    @GetMapping("/cm/login")
    public String login() {
      return "login";
    }
  }
}
