package sjjd.doc.line.socket;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sjjd.doc.line.pojo.CustomProtocol;
import sjjd.doc.line.pojo.Facility;
import sjjd.doc.line.pojo.FacilityExample;
import sjjd.doc.line.service.IFacilityService;
import sjjd.doc.line.util.NettySocketHolder;
import sjjd.doc.line.util.SocketByteSend;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component
public class HeartBeatSimpleHandle extends SimpleChannelInboundHandler<CustomProtocol> {

    public static HeartBeatSimpleHandle heartBeatSimpleHandle;

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatSimpleHandle.class);
    private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(JSONObject.fromObject(new CustomProtocol("123456", "pong")).toString() + "\n", CharsetUtil.UTF_8));

    @Resource
    private IFacilityService facilityService;


    //3.容器初始化的时候进行执行-这里是重点
    @PostConstruct
    public void init() {
        heartBeatSimpleHandle = this;
        heartBeatSimpleHandle.facilityService = this.facilityService;
    }

    /**
     * 取消绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("进入删除方法");
        NioSocketChannel nio = (NioSocketChannel) ctx.channel();
        String key = NettySocketHolder.getMapKey(nio);
        System.out.println(key);
        FacilityExample facEx = new FacilityExample();


        if (key != "0") {
            System.out.println("删除clientid为" + key);
            facEx.createCriteria().andFacClientidEqualTo(key);
            Facility fac = new Facility();
            fac.setFacClientid("");
            fac.setFacState("0");
            heartBeatSimpleHandle.facilityService.updateByExampleSelective(fac, facEx);
            System.out.println("删除成功");
        }
        NettySocketHolder.remove((NioSocketChannel) ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                LOGGER.info("已经50秒没有收到信息！！");
                //向客户端发送消息
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("id", ctx.channel().id().toString());
                jsonobj.put("type", "reconnection");
                jsonobj.put("data", null);
                //向客户端发送消息
                SocketByteSend.NettySendSocket(ctx, JSONObject.fromObject(jsonobj).toString());
                NioSocketChannel nio = (NioSocketChannel) ctx.channel();
                String key = NettySocketHolder.getMapKey(nio);
                FacilityExample facEx = new FacilityExample();
                if (key != "0") {
                    System.out.println("删除clientid为" + key);
                    facEx.createCriteria().andFacClientidEqualTo(key);
                    Facility fac = new Facility();
                    fac.setFacClientid("");
                    fac.setFacState("0");
                    heartBeatSimpleHandle.facilityService.updateByExampleSelective(fac, facEx);
                    System.out.println("删除成功");
                }
                NettySocketHolder.remove((NioSocketChannel) ctx.channel());
                ctx.disconnect();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol customProtocol) throws Exception {
//        LOGGER.info("收到customProtocol={}", customProtocol);
//        System.out.println(customProtocol);
        //我们调用writeAndFlush（Object）来逐字写入接收到的消息并刷新线路
        //ctx.writeAndFlush(customProtocol);
        //保存客户端与 Channel 之间的关系sad

//        System.out.println(customProtocol.getData());
//        JSONObject jsonobject = JSONObject.fromObject(customProtocol.getData());
//        Admin admin= (Admin)JSONObject.toBean(jsonobject,Admin.class);
        if (customProtocol.getType().equals("ping")) {
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("type", "pong");
            jsonobj.put("date", new Date().getTime());
            SocketByteSend.NettySendSocket(ctx, JSONObject.fromObject(jsonobj).toString());
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettySocketHolder.put(ctx.channel().id().toString(), (NioSocketChannel) ctx.channel());
        System.out.println("链接成功");
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("id", ctx.channel().id().toString());
        jsonobj.put("type", "init");
        SocketByteSend.NettySendSocket(ctx, JSONObject.fromObject(jsonobj).toString());
    }
}
