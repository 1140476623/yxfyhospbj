package sjjd.doc.line.config.wxpay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xyw
 * @version 1.0
 * @description 微信公众号开发配置类
 * @date 2020/07/29
 */
@Component
public class WxPayConfig {

    /**
     * 开发者ID
     */
    public static String appID;

    @Value("${wx.appID}")
    public void setAppID(String appID) {
        this.appID = appID;
    }


    /**
     * 商户号
     */
    public static String mchID;

    @Value("${wx.mchID}")
    public void setMchID(String mchID) {
        this.mchID = mchID;
    }


    /**
     * API密钥
     */
    public static String key;

    @Value("${wx.key}")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 统一下单-通知链接
     */
    public static String notifyUrl;

    @Value("${wx.notifyUrl}")
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /**
     * 连接超时时间
     */
    public static Integer connectionTimeout;

    @Value("${https.connectionTimeout}")
    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * 连接超时时间
     */
    public static Integer readTimeout;

    @Value("${https.readTimeout}")
    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }
}
