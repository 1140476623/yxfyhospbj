package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class RegisterInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 挂号单据号
     */
    private String registerNo;

    /**
     * 患者姓名
     */
    private String residentName;

    /**
     * 诊室名称
     */
    private String deptName;

    /**
     * 医生姓名
     */
    private String docName;

    /**
     * 挂号时间
     */
    private String registerDate;

    /**
     * 就诊时间
     */
    private String eventDate;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 证件号码
     */
    private String idNum;

    private String numCome;


}
