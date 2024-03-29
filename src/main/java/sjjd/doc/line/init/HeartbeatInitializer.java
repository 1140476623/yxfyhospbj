package sjjd.doc.line.init;


import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import sjjd.doc.line.encode.HeartbeatDecoder;
import sjjd.doc.line.socket.HeartBeatSimpleHandle;


/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public class HeartbeatInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                //五秒没有收到消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(50, 0, 0))
                .addLast(new HeartbeatDecoder())
                .addLast(new HeartBeatSimpleHandle());
    }
}
