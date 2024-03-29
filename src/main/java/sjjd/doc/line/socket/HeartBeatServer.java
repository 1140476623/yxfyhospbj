package sjjd.doc.line.socket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sjjd.doc.line.init.HeartbeatInitializer;
import sjjd.doc.line.pojo.Facility;
import sjjd.doc.line.pojo.FacilityExample;
import sjjd.doc.line.service.IFacilityService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component
public class HeartBeatServer implements ApplicationRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatServer.class);
    /**
     * NioEventLoopGroup是一个处理I / O操作的多线程事件循环。 Netty为不同类型的传输提供各种EventLoopGroup实现。
     * 我们在此示例中实现了服务器端应用程序，因此将使用两个NioEventLoopGroup。第一个，通常称为“老板”，接受传入连接。第二个，通常称为“工人”，
     * 一旦老板接受连接并将接受的连接注册到工作人员，就处理被接受连接的流量。使用了多少个线程以及它们如何映射到创建的Channels取决于EventLoopGroup实现，甚至可以通过构造函数进行配置。
     */
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${netty.server.port}")
    private int nettyPort;

    public static HeartBeatServer heartBeatServer;

    @Resource
    private IFacilityService facilityService;


    @PostConstruct
    public void start() throws InterruptedException {


    }

    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("关闭 Netty 成功");
    }


    /**
     * 启动 Netty
     *
     * @return
     * @throws InterruptedException
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new HeartbeatInitializer());
        //绑定并开始接受传入的连接。
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            LOGGER.info("启动 Netty 成功");
        }

        heartBeatServer = this;
        heartBeatServer.facilityService = this.facilityService;
        LOGGER.info("初始化方法,清空所有socket链接数据");
        Facility facility = new Facility();
        facility.setFacClientid("");
        facility.setFacState("0");
        heartBeatServer.facilityService.updateByExampleSelective(facility, new FacilityExample());
    }
}
