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
public class H264NALProcessor {

  private ByteBuf buffer = Unpooled.buffer(0);
  private int bufferActualDataSize = 0;
  private ByteBuf separator = Unpooled.wrappedBuffer(Utils.hToB("00000001"));
  private final Consumer<byte[]> resultConsumer;

  public void process(byte[] _chunk) {


    // System.out.println(Utils.bToH(_chunk));
    // ByteBuf chunk = Unpooled.wrappedBuffer(_chunk);

    // final int chunkSize = _chunk.length;
    // final int bufferRemaindCapacity = buffer.capacity() - bufferActualDataSize;

    // if (chunkSize >  bufferRemaindCapacity) {
    //   int newBufferSize =  bufferActualDataSize + chunkSize;
    //   ByteBuf newExpanededBuffer = Unpooled.buffer(newBufferSize);

    //   buffer.getBytes(0, newExpanededBuffer, buffer.capacity());
    //   buffer = newExpanededBuffer;
    // }
    // chunk.getBytes(0, buffer, chunk.capacity());
    // bufferActualDataSize = bufferActualDataSize + chunk.capacity();

    // do {

    //   byte[] inspectTargetData = new byte[buffer.capacity() - separator.capacity()];
    //   buffer.getBytes(separator.capacity(), inspectTargetData, 0, buffer.capacity() - separator.capacity());

    //   final int index_NAL = Bytes.indexOf(inspectTargetData, separator.array());

    //   if (index_NAL == -1) {
    //     break;
    //   }

    //   byte[] frameBytes = new byte[index_NAL];
    //   buffer.getBytes(separator.capacity(), frameBytes, 0, index_NAL);

    //   final int totalOutputLength = separator.capacity() + index_NAL;


    //   ByteBuf removedOutputBuffer
    //     = Unpooled.buffer(buffer.capacity() - totalOutputLength);
    //   buffer.getBytes(totalOutputLength, removedOutputBuffer, buffer.capacity() - totalOutputLength);
    //   buffer = removedOutputBuffer;
    //   bufferActualDataSize = buffer.capacity();

    //   byte[] frameWithNAL = frameBytes;
    //   if (frameWithNAL.length != 0) {
    //     System.out.print(", result.length : " + Bytes.concat(this.separator.array(), frameWithNAL).length);
    //     System.out.println();
    //     System.out.println(Utils.bToH(frameWithNAL).toString().substring(0, 10).toLowerCase());
    //     System.out.println(Utils.bToH(frameWithNAL).toString().substring(Utils.bToH(frameWithNAL).length() - 10, Utils.bToH(frameWithNAL).length()).toLowerCase());
    //   }

    //   resultConsumer.accept(Bytes.concat(this.separator.array(), frameBytes));
    // } while (true);
  }
}