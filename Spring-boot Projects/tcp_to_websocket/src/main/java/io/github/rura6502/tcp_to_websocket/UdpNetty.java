package io.github.rura6502.tcp_to_websocket;

import org.springframework.scheduling.annotation.Async;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.udt.nio.NioUdtByteAcceptorChannel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * UdpNetty
 */
@RequiredArgsConstructor
@Slf4j
@ToString
public class UdpNetty {

  
  @Getter
  private final int port;

  private final UdpHandler handler;

  private ChannelFuture f = null;

  EventLoopGroup bossGroup = new NioEventLoopGroup();
  Bootstrap b = new Bootstrap();

  @Async
  public void run() {
    log.info("UdpNetty start with port = {}", port);
    
    try {
      b.group(bossGroup).channel(NioDatagramChannel.class)
          // .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 0)
          // .option(ChannelOption.AUTO_READ, false)

          .handler(new ChannelInitializer<DatagramChannel>() {
            @Override
            protected void initChannel(DatagramChannel ch) throws Exception {
              ch.pipeline().addLast(handler);
            }
          })
          .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 10)
        
          // .option(ChannelOption.SO_BACKLOG, 128)
          // .childOption(ChannelOption.SO_KEEPALIVE, true);
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
}