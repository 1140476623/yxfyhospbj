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
public class Dictionaries implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String names;

    /**
     * 1 电子健康卡申请方式
     * 2 证件类型
     * 3 性别
     * 4 民族
     * 5 认证模式
     * 6 默认关联卡类型
     * 7 婚姻状况
     * 8 职业分类代码
     * 9 国家和地区
     */
    private String type;


}
