package io.github.rura6502;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * UdpServer
 */
public class UdpServer {

  private int port;

  public UdpServer(int port) {
    this.port = port;
  }

  public void run() throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(bossGroup).channel(NioDatagramChannel.class).handler(new ChannelInitializer<DatagramChannel>() { // (4)

        @Override
        protected void initChannel(DatagramChannel ch) throws Exception {
          // TODO Auto-generated method stub
          ch.pipeline().addLast(new CustomHandler())
          .addLast(new StringDecoder(CharsetUtil.UTF_8));
        }
      });

      // Bind and start to accept incoming connections.
      ChannelFuture f = b.bind(port).sync(); // (7)

      System.out.println("start server with port : " + this.port);

      // Wait until the server socket is closed.
      // In this example, this does not happen, but you can do that to gracefully
      // shut down your server.
      f.channel().closeFuture().sync();
    } finally {
      // bossGroup.shutdownGracefully();
    }
  }
}

class CustomHandler extends SimpleChannelInboundHandler<DatagramPacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

    ByteBuf buf = msg.content();
    byte[] bytes = new byte[buf.readableBytes()];
    int readerIndex = buf.readerIndex();
    buf.getBytes(readerIndex, bytes);
    

    

    System.out.print("from : " + msg.sender().getAddress());
    System.out.println(" data : " + new String(bytes));
  }

}