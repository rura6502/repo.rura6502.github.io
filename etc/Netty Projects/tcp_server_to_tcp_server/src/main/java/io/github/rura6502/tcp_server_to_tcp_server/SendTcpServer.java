package io.github.rura6502.tcp_server_to_tcp_server;

import java.net.InetSocketAddress;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SendTcpServer
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class SendTcpServer {

  final int port = 9999;

  TcpInboundHandler handler = new TcpInboundHandler();

  ChannelFuture f = null;

  EventLoopGroup bossGroup = new NioEventLoopGroup();
  EventLoopGroup workerGroup = new NioEventLoopGroup();

  ServerBootstrap b = new ServerBootstrap();

  @Async
  public void run() {
    log.info("NettyServer start with port = {}", port);

    try {
      b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
      .localAddress(new InetSocketAddress(9999))
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(handler);
            }
          }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
      log.info("NettyServer started with port = {}", port);
      f = b.bind().sync();
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

  @Sharable
  class TcpInboundHandler extends ChannelInboundHandlerAdapter {

    ChannelHandlerContext ctx;

    public void sendMessage(String message) {
      if (ctx != null) {
        ByteBuf buf = Unpooled.wrappedBuffer(message.getBytes());
        ctx.writeAndFlush(buf);
      }
    }

    public void sendMessage(byte[] bytes) {
      if (ctx != null) {
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        ctx.writeAndFlush(buf);
      }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf buf = (ByteBuf) msg;
      ctx.writeAndFlush(buf);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      // super.exceptionCaught(ctx, cause);
      cause.printStackTrace();
      ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
      // super.channelRegistered(ctx);
      System.out.println("Hello, New Guy!");
      this.ctx = ctx;
    }
  }
}

