package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class PrintInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 自助机mac
     */
    @ApiModelProperty("自助机mac")
    private String terminalMac;

    /**
     * 打印设备的状态（0-不正常，1-正常）
     */
    @ApiModelProperty("打印设备的状态（0-不正常，1-正常）")
    private Integer status;

    /**
     * 打印设备类型（1票据打印机，2报告打印机）
     */
    @ApiModelProperty("打印设备类型（1票据打印机，2报告打印机）")
    private String printType;

    /**
     * 故障原因
     */
    @ApiModelProperty("故障原因")
    private String reason;

    /**
     * 打印设备的唯一标识码
     */
    @ApiModelProperty("打印设备的唯一标识码")
    private String printMac;


}
