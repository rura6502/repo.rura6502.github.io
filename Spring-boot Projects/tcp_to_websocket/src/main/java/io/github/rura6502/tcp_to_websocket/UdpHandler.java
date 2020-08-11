package io.github.rura6502.tcp_to_websocket;

import java.io.IOException;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.RequiredArgsConstructor;

/**
 * UdpHandler
 */
@RequiredArgsConstructor
public class UdpHandler
  extends SimpleChannelInboundHandler<DatagramPacket> {


  private final H264NALProcessor process;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
    // super.channelRead(ctx, msg);
    ByteBuf buf = msg.content();
    byte[] received = new byte[buf.readableBytes()];
    buf.readBytes(received);
    process.process(received);

      // System.out.println("send bytes length : " + bytes.length);
      



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


/**
 * UdpHandler
 */
// @Component
// @RequiredArgsConstructor
// public class UdpHandler
//   extends ChannelInboundHandlerAdapter {

//   private final WebSocketConfiguration wsConf;

//   // private final Consumer<byte[]> streamConsumer;

//   @Override
//   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//     // super.channelRead(ctx, msg);
//     DatagramPacket packet = (DatagramPacket) msg;
//     ByteBuf buf = packet.content();
//     byte[] bytes = new byte[buf.readableBytes()];
//     buf.readBytes(bytes);
//     System.out.println("send bytes length : " + bytes.length);
//     // wsConf.getSessions().parallelStream().forEach(session -> {
//     //   try {
//     //     session.sendMessage(new BinaryMessage(bytes));
//     //   } catch (IOException e) {
//     //     e.printStackTrace();
//     //   }
//     // });



//     // String getStr = buf.toString(Charset.forName("UTF8"));
//     // wsConf.getSessions().parallelStream().forEach(ws -> {
//     //   try {
//     //     ws.sendMessage(new TextMessage(getStr.getBytes()));
//     //   } catch (IOException e) {
//     //     // TODO Auto-generated catch block
//     //     e.printStackTrace();
//     //   }
//     // });
//   }

//   @Override
//   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//     // super.exceptionCaught(ctx, cause);
//     cause.printStackTrace();
//     ctx.close();
//   }
// }