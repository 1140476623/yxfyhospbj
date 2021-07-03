package sjjd.doc.line.encode;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sjjd.doc.line.pojo.CustomProtocol;
import sjjd.doc.line.util.SocketByteSend;

import java.util.Arrays;
import java.util.List;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 * 服务端解码器
 */
public class HeartbeatDecoder extends ByteToMessageDecoder {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartbeatDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        byte[] bytes1 = Arrays.copyOfRange(bytes, 8, bytes.length);
        byte[] bytes2 = Arrays.copyOfRange(bytes, 0, 4);
        String content = new String(bytes1);
        Integer integer = SocketByteSend.bytes2Int(bytes2) - 8;
        JSONObject jsonobject = JSONObject.fromObject(content);
        CustomProtocol pool = (CustomProtocol) JSONObject.toBean(jsonobject, CustomProtocol.class);
        CustomProtocol customProtocol = new CustomProtocol();
        customProtocol.setId(pool.getId());
        customProtocol.setData(pool.getData());
        customProtocol.setType(pool.getType());
        list.add(customProtocol);
        if (!integer.equals(bytes1.length)) {
            System.out.println(integer);
            System.out.println(bytes1.length);
            LOGGER.info("长度错误:" + pool.getId() + pool.getType());
        }
    }
}
