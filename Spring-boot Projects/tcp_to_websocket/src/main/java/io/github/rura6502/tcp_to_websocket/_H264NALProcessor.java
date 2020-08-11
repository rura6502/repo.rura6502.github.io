package io.github.rura6502.tcp_to_websocket;

import java.util.function.Consumer;

import com.google.common.primitives.Bytes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.RequiredArgsConstructor;

/**
 * Tmp
 */
@RequiredArgsConstructor
public class _H264NALProcessor {

  private int offset = 0;
  private int bodyOffset = 0;
  private int bufferSize = 1024 * 1024 * 1; //1MB
  private double bufferFlush = bufferSize * 0.1;
  private ByteBuf buffer =  Unpooled.wrappedBuffer(new byte[bufferSize]);
  private ByteBuf separator = Unpooled.wrappedBuffer(Utils.hToB("00000001"));

  private final Consumer<byte[]> resultConsumer;

  public void process(byte[] _chunk) {

    // resultConsumer.accept(_chunk);

    ByteBuf chunk = Unpooled.wrappedBuffer(_chunk);
    int chunkLength = chunk.capacity();

    // System.out.println("-----------------------------------------------");
    // System.out.println("chunk.capacity() : " + chunk.capacity());
    // System.out.println("offset : " + this.offset);
    // System.out.println("bodyOffset : " + this.bodyOffset);
    // System.out.println("bufferSize : " + this.bufferSize);
    // System.out.println("buffer.capacity() : " + this.buffer.capacity());
    

    if (this.offset + chunkLength > this.bufferSize - this.bufferFlush) {
      int minimalLength = this.bufferSize - this.bodyOffset + chunkLength;
      if (this.bufferSize < minimalLength) {
        this.bufferSize = minimalLength;
      }
      byte[] tmp1 = new byte[this.bufferSize];
      this.buffer.getBytes(0, tmp1, 0, this.buffer.capacity());
      this.buffer = Unpooled.wrappedBuffer(tmp1);
      this.offset = this.offset - this.bodyOffset;
      this.bodyOffset = 0;
    }

    chunk.getBytes(0, this.buffer, this.offset, chunk.capacity());

    int i;
    int start;
    int stop = this.offset + chunkLength;

    do {
      start = Math.max(this.bodyOffset, this.offset - this.separator.capacity());
      byte[] a = this.buffer.copy(start, stop - start).array();
      
      i = Bytes.indexOf(a, this.separator.array());
      System.out.print("start : " + start);
      System.out.print(", end : " + (stop - start));
      System.out.print(", " + Utils.bToH(new byte[]{a[0], a[1],a[2], a[3],a[4], a[5]}));
      System.out.print(" ~ ");
      System.out.print(Utils.bToH(new byte[]{a[a.length-6], a[a.length-5],a[a.length-4], a[a.length-3],a[a.length-2], a[a.length-1]}));
      System.out.println(", indexOf: " + i);

      // if (start == 66236) {
      //   for (int x=0; x<20; x++) {
      //     System.out.print(a[x] + ", ");
      //   }
      //   System.out.println();
      // }

      if (i == -1) {
        break;
      }
      i += start;
      byte[] img = this.buffer.copy(this.bodyOffset, i - this.bodyOffset).array();

      if (img.length != 0) {
        System.out.print(", start : " + start);
        System.out.print(", i : " + i);
        System.out.print(", offset : " + this.offset);
        System.out.print(", bodyOffset : " + this.bodyOffset);
        System.out.print(", buffer.length : " + this.buffer.capacity());
        System.out.print(", result.length : " + Bytes.concat(this.separator.array(), img).length);
        System.out.println();
        System.out.println(Utils.bToH(img).toString().substring(0, 20).toLowerCase());
        System.out.println(Utils.bToH(img).toString().substring(Utils.bToH(img).length() - 20, Utils.bToH(img).length()).toLowerCase());
        resultConsumer.accept(Bytes.concat(this.separator.array(), img));
      }
      

      this.bodyOffset = i + this.separator.capacity();
    } while (true);
    this.offset += chunkLength;
  }
}