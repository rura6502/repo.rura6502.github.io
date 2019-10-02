/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.rura6502;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;

public class App {

  public static void main(String[] args) throws InterruptedException {
    Writter writter = new Writter();

    TestThread r1 = new TestThread(writter, 10);
    TestThread r2 = new TestThread(writter, 15);
    TestThread r3 = new TestThread(writter, 13);
    
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    Thread t3 = new Thread(r3);

    t1.start();
    t2.start();
    t3.start();

    Thread.sleep(10000);
    
    r1.stop();
    r2.stop();
    r3.stop();
  }
}

class Writter {
  BlockingQueue<String> strQueue = new LinkedBlockingQueue<>();

  public Writter() {
    writeToFile();
  }

  public void writeToFile() {
    new Thread(() -> {
      while (true) {
        System.out.println("queue size is " + strQueue.size());
        if (!strQueue.isEmpty()) {
          try {
            String now = LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond();
            File file = new File("C:/Users/abcd/Desktop/" + now + ".txt");
            BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(file));
            List<String> strArr = new ArrayList<>();
            strQueue.drainTo(strArr);
            bs.write(StringUtils.join(strArr, "\n").getBytes());
            bs.close();
          } catch (IOException e) {
          }
        }
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public void pushStr(String str) {
    this.strQueue.offer(str);
  }
}

class TestThread implements Runnable {
  Writter writter;
  long interval;
  boolean stopFlag = false;
  int count = 0;

  TestThread(Writter writter, long interval) {
    this.writter = writter;
    this.interval = interval;
  }

  @Override
  public void run() {
    while (!stopFlag) {
      String now = LocalTime.now().getSecond() + "_" + LocalTime.now().getNano();
      writter.pushStr(Thread.currentThread().getName() + " (" + (count++) + ")" + now);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        Thread.sleep(this.interval);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Thread.currentThread().getName() + " is stoped, count : " + count);
  }

  public void stop() {
    this.stopFlag = true;
  }
}