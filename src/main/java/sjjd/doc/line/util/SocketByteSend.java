package sjjd.doc.line.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class SocketByteSend {

    public static void NettySendSocket(NioSocketChannel nioSocketChannel, String msg) {
        byte[] msgBytes = msg.getBytes(CharsetUtil.UTF_8);
        Integer msgLength = 8 + msgBytes.length;
        byte[] bytes = new byte[msgLength];
        byte[] bytes1 = int2Bytes(msgLength);
        System.arraycopy(bytes1, 0, bytes, 0, bytes1.length);
        bytes[4] = 0;
        bytes[5] = 0;
        bytes[6] = 0;
        bytes[7] = 0;
        System.arraycopy(msgBytes, 0, bytes, 8, msgBytes.length);
        ByteBuf bf = Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(bytes));
        if (nioSocketChannel != null) {
            nioSocketChannel.writeAndFlush(bf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }
    }

    public static void NettySendSocket(ChannelHandlerContext nioSocketChannel, String msg) {
        byte[] msgBytes = msg.getBytes(CharsetUtil.UTF_8);
        Integer msgLength = 8 + msgBytes.length;
        byte[] bytes = new byte[msgLength];
        byte[] bytes1 = int2Bytes(msgLength);
        System.arraycopy(bytes1, 0, bytes, 0, bytes1.length);
        bytes[4] = 0;
        bytes[5] = 0;
        bytes[6] = 0;
        bytes[7] = 0;
        System.arraycopy(msgBytes, 0, bytes, 8, msgBytes.length);
        ByteBuf bf = Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(bytes));
        if (nioSocketChannel != null) {
            nioSocketChannel.writeAndFlush(bf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }
    }

    public static byte[] int2Bytes(int integer) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (integer >> 24);
        bytes[2] = (byte) (integer >> 16);
        bytes[1] = (byte) (integer >> 8);
        bytes[0] = (byte) integer;
        return bytes;
    }

    public static int bytes2Int(byte[] bytes) {
        //如果不与0xff进行按位与操作，转换结果将出错，有兴趣的同学可以试一下。
        int int1 = bytes[0] & 0xff;
        int int2 = (bytes[1] & 0xff) << 8;
        int int3 = (bytes[2] & 0xff) << 16;
        int int4 = (bytes[3] & 0xff) << 24;

        return int1 | int2 | int3 | int4;
    }

}
