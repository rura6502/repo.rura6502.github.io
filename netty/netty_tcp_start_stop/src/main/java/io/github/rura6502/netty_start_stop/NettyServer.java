package io.github.rura6502.netty_start_stop;

import java.util.function.Consumer;

import org.springframework.scheduling.annotation.Async;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * NettyServer
 */
@RequiredArgsConstructor
@Slf4j
@ToString
public class NettyServer {

  @NonNull
  @Getter
  private int port;

  private ChannelFuture f = null;

  EventLoopGroup bossGroup = new NioEventLoopGroup();
  EventLoopGroup workerGroup = new NioEventLoopGroup();

  ServerBootstrap b = new ServerBootstrap();

  @Async
  public void run() {
    log.info("NettyServer start with port = {}", port);
    
    try {
      b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 0)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new StreamSocketServerHandler());
            }
          }).option(ChannelOption.SO_BACKLOG, 128)
          .childOption(ChannelOption.SO_KEEPALIVE, true);
      log.info("NettyServer started with port = {}", port);
      f = b.bind(port).sync();
      log.info("NettyServer is listening with port = {}", port);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Async
  public void stop() {
    
    try {
      
      log.info("start  -- workerGroup.shutdownGracefully().sync()");
      workerGroup.shutdownGracefully().sync();
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

  @RequiredArgsConstructor
  class StreamSocketServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      // super.channelRead(ctx, msg);
      ByteBuf buf = (ByteBuf) msg;
      System.out.println(buf.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      // super.exceptionCaught(ctx, cause);
      cause.printStackTrace();
      ctx.close();
    }
    
  }

  
}