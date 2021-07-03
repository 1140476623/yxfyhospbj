package sjjd.doc.line.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public class NettySocketHolder {

    private static final Map<String, NioSocketChannel> MAP = new ConcurrentHashMap<>(16);

    public static void put(String id, NioSocketChannel socketChannel) {
        MAP.put(id, socketChannel);
    }

    public static NioSocketChannel get(String id) {
        return MAP.get(id);
    }

    public static Map<String, NioSocketChannel> getMAP() {
        return MAP;
    }

    public static void remove(NioSocketChannel nioSocketChannel) {
        MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> MAP.remove(entry.getKey()));
    }

    public static String getMapKey(NioSocketChannel nioSocketChannel) {
        String key = "0";
        Iterator<Entry<String, NioSocketChannel>> it = MAP.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, NioSocketChannel> entry = it.next();
            if (entry.getValue() == nioSocketChannel) {
                key = entry.getKey();
            }

        }

        return key;
    }
}
