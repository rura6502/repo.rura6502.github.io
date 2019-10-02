package io.github.rura6502.spring_websocket_stomp;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Message
 */
@Data
@AllArgsConstructor
public class Message {

  LocalDateTime timestamp;
  String title;
  String content;

}