package sjjd.doc.line.config.wxpay;

/**
 * @author xyw
 * @version 1.0
 * @description 微信公众号常量类
 * @date 2020/07/29
 */
public class WxPayConstants {

    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 统一下单-扫描支付
     */
    public static String PAY_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 统一下单-查询订单
     */
    public static String PAY_ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 统一下单-关闭订单
     */
    public static String PAY_CLOSE_ORDER = "https://api.mch.weixin.qq.com/pay/closeorder";
    /**
     * 统一下单-申请退款
     */
    public static String PAY_ORDER_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 统一下单-退款查询
     */
    public static String PAY_REFUND_QUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
    /*
     * 支付码支付
     */
    public static String MICRO_PAY = "https://api.mch.weixin.qq.com/pay/micropay";

    /**
     * 请求成功返回码
     */
    public final static String ERRCODE_OK_CODE = "0";
    /**
     * 错误的返回码的Key
     */
    public final static String ERRCODE = "errcode";

    /**
     * 返回状态码
     */
    public final static String RETURN_CODE = "return_code";
    /**
     * 返回状态码
     */
    public final static String RESULT_CODE = "result_code";
    /**
     * 成功SUCCESS字符串
     */
    public final static String SUCCESS = "SUCCESS";
    /**
     * trade_type 微信NATIVE扫码支付
     */
    public final static String TRADE_TYPE = "NATIVE";

    /**
     * access_token 字符串
     */
    public final static String ACCESS_TOKEN = "access_token";

    /**
     * 签名类型 MD5
     */
    public final static String SING_MD5 = "MD5";

    /**
     * 签名类型 HMAC-SHA256
     */
    public final static String SING_HMACSHA256 = "HMAC-SHA256";

}
