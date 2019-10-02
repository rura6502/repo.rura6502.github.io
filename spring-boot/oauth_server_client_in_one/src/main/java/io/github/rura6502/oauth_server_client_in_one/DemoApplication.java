package io.github.rura6502.oauth_server_client_in_one;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}


@RestController
class TestController {
  @GetMapping("/test")
  public ResponseEntity<Principal> get(final Principal principal) {
    return ResponseEntity.ok(principal);
  }
  @GetMapping("/admin")
  @Secured({"ROLE_ADMIN"})
  public String test1(final Principal principal) {
    return "I am test1, only for ADMIN";
  }
  @GetMapping("/user")
  @PreAuthorize("hasAnyAuthority('USER')")
  public String test2(final Principal principal) {
    return "I am test1, only for USER";
  }

  // @Secured({"ROLE_ADMIN", "ROLE"U})
  @GetMapping("/admin_user")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public String test3(final Principal principal) {
    return "I am test1, only for USER AND ADMIN";
  }
}