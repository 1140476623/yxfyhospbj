package sjjd.doc.line.util;

import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketRedisUtil {

    @Resource
    private RedisTemplate<String, Map<Long, NioSocketChannel>> redisTemplate;


    public void put(Long id, NioSocketChannel socketChannel) {
        System.out.println("是否有此对象" + redisTemplate.opsForValue().get("aId2"));
        if (redisTemplate.hasKey("netty")) {
            Map<Long, NioSocketChannel> map = redisTemplate.opsForValue().get("netty");
            map.put(id, socketChannel);
            redisTemplate.opsForValue().set("netty", map);
        } else {
            Map<Long, NioSocketChannel> map = new HashMap<>();
            map.put(id, socketChannel);
            redisTemplate.opsForValue().set("netty", map);
        }
    }

    public NioSocketChannel get(Long id) {
        Map<Long, NioSocketChannel> map = redisTemplate.opsForValue().get("netty");
        NioSocketChannel cel = map.get(id);
        return cel;
    }

    public void remove(NioSocketChannel nioSocketChannel) {
        Map<Long, NioSocketChannel> map = redisTemplate.opsForValue().get("netty");
        map.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> map.remove(entry.getKey()));
    }


}
