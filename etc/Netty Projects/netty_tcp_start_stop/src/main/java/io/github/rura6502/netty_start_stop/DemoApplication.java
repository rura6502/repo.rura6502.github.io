package io.github.rura6502.netty_start_stop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }
  
  

}



@RestController
  class TestController {

    public static Map<Integer, NettyServer> serverList = new HashMap<>();

    
    @GetMapping("/start/{port}")
    public void start(@PathVariable("port") int port) {
      System.out.println("ask port");
      NettyServer server = new NettyServer(port);
      serverList.put(port, server);
      server.run();
    }

    @GetMapping("/stop/{port}")
    public void stop(@PathVariable("port") int port) {
      System.out.println("ask stop");
      serverList.remove(port).stop();
    }

    @GetMapping("/list")
    public Map<Integer, NettyServer> list() {
      System.out.println("ask list");
      return serverList;
    }
  }