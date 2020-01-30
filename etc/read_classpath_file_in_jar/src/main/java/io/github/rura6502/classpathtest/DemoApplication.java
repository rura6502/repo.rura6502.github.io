package io.github.rura6502.classpathtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;


public class DemoApplication {
  public static void main(String[] args) throws IOException {
    InputStream is = DemoApplication.class.getClassLoader().getResourceAsStream("testfile/test1.txt");
    System.out.println(is == null);
    // String content = IOUtils.toString(is, StandardCharsets.UTF_8.name());
    // System.out.println(content);
    // File file = File.createTempFile("test", "txt");
    // IOUtils.copy(is, new FileOutputStream(file));
    // System.out.println(file.exists());
    // File file 


  }
}
