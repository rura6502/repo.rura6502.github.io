package io.github.rura6502.hls_streaming;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }



  @Controller
  class Test {
    @GetMapping("/")
    public String index() {
      return "index";
    }
  }

  @RestController
  class RestTest {
    @GetMapping(value = "/stream/{streamFileName}")
    public ResponseEntity<Resource> m3u8(
      HttpServletRequest request
      , @PathVariable(name = "streamFileName", required = false) String streamFileName
    ) throws IOException {
      Resource resource = new FileUrlResource("C:/Users/abcd/Desktop/ffmpeg/bin/" + streamFileName);
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
      .body(resource);
    }

    // @GetMapping(value = "/stream/{streamFileName}")
    // public ResponseEntity<Resource> m3u8partFile(
    //   HttpServletRequest request
    //   , @PathVariable("streamFileName") String streamFileName) throws IOException {
    //   Resource resource = new FileUrlResource("C:/Users/abcd/Desktop/ffmpeg/bin/" + streamFileName);
    //   return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
      
    //   .body(resource);
    // }
  }
}
