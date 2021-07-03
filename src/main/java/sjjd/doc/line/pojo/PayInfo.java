package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    //支付中
    public static final String STATUS_PAYING_APPLY = "0";
    //支付成功
    public static final String STATUS_PAYING_SUCCESS = "1";
    //支付失败
    public static final String STATUS_PAYING_FAIL = "2";
    //当前订单关闭
    public static final String STATUS_ClOSE = "3";
    //已退款
    public static final String STATUS_REFUND = "4";

    //支付方式--微信支付
    public static final String WX_PAY = "微信支付";

    //返回的成功状态码
    public static final String RETURN_CODE = "0000";

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 挂号单号
     */
    private String registerNo;

    /**
     * 单据号
     */
    private String documentNo;

    /**
     * 患者姓名
     */
    private String residentName;

    /**
     * 就诊id
     */
    private String eventId;

    /**
     * 患者id
     */
    private String residentId;

    /**
     * 客户端ip
     */
    private String clintIp;

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 支付状态0-未支付，1已支付，2支付失败,3订单关闭，4已退款
     */
    private String payStatus;

    /**
     * 金额
     */
    private BigDecimal totalFee;

    /**
     * 交易名称信息
     */
    private String subject;

    /**
     * 医生姓名
     */
    private String docName;

    /**
     * 科室名称
     */
    private String deptName;
    /**
     * 支付方式-微信支付，支付宝支付
     */
    private String payType;
    /**
     * 备注原因
     */
    private String reason;
    /**
     * 费用类型--挂号，检查
     */
    private String type;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间(支付时间)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;


    private String facilityCode;


}
