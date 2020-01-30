package io.github.rura6502.tcp_to_websocket;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;

/**
 * StreamSocketServerHandler
 */
@Component
@RequiredArgsConstructor
public class StreamSocketServerHandler extends ChannelInboundHandlerAdapter {

  private final WebSocketConfiguration wsConf;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // super.channelRead(ctx, msg);
    ByteBuf buf = (ByteBuf) msg;
    // while (buf.isReadable()) {
      byte[] bytes = new byte[buf.readableBytes()];
      buf.readBytes(bytes);
      System.out.println("send bytes length : " + bytes.length);
      wsConf.getSessions().parallelStream().forEach(session -> {
        try {
          session.sendMessage(new BinaryMessage(bytes));
        } catch (IOException e) {
          e.printStackTrace();
        }
      });



    // String getStr = buf.toString(Charset.forName("UTF8"));
    // wsConf.getSessions().parallelStream().forEach(ws -> {
    //   try {
    //     ws.sendMessage(new TextMessage(getStr.getBytes()));
    //   } catch (IOException e) {
    //     // TODO Auto-generated catch block
    //     e.printStackTrace();
    //   }
    // });
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // super.exceptionCaught(ctx, cause);
    cause.printStackTrace();
    ctx.close();
  }
}