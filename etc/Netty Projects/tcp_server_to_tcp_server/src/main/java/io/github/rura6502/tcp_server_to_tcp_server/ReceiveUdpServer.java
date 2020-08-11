package io.github.rura6502.tcp_server_to_tcp_server;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ReceiveTcpServer
 */
@Slf4j
@RequiredArgsConstructor
public class ReceiveUdpServer {

  final int port = 5555;

  ChannelFuture f = null;

  EventLoopGroup bossGroup = new NioEventLoopGroup();
  Bootstrap b = new Bootstrap();

  // private final Consumer<byte[]> udpConsumer;

  UdpHandler udpHandler;

  private final H264NALProcessor process;

  @Async
  public void run() {
    log.info("UdpNetty start with port = {}", port);
    udpHandler = new UdpHandler(process);
    
    try {
      b.group(bossGroup).channel(NioDatagramChannel.class)
          .handler(new ChannelInitializer<DatagramChannel>() {
            @Override
            protected void initChannel(DatagramChannel ch) throws Exception {
              ch.pipeline().addLast(udpHandler);
            }
          }).option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 1000)
          ;
      log.info("UdpNetty started with port = {}", port);
      f = b.bind(port).sync();
      log.info("UdpNetty is listening with port = {}", port);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Async
  public void stop() {
    
    try {
      log.info("start  -- bossGroup.shutdownGracefully().sync()");
      bossGroup.shutdownGracefully().sync();
      log.info("start  -- f.channel().closeFuture().sync()");
      f.channel().closeFuture().sync();
      
      
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      
      
      log.info("RelayServer was terminated, port = {}", port);
    }
  }

  @Sharable
  @RequiredArgsConstructor
  class UdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private final H264NALProcessor process;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
      ByteBuf buf = msg.content();
      byte[] bytes = new byte[buf.readableBytes()];
      buf.readBytes(bytes);
      // ByteBuffer chunk = buf.nioBuffer();
      process.process(bytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
  }
}

