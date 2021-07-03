package sjjd.doc.line.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by xyw
 * @date 2021/3/23.
 */
@Data
public class News {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("新闻标题")
    private String title;

    @ApiModelProperty("新闻首页图片")
    private String image;

    @ApiModelProperty("新闻内容")
    private String content;

    @ApiModelProperty("是否置顶0 -否 1-是")
    private String top;

    @ApiModelProperty("是否启用0 -否 1-是")
    private String state;

    @ApiModelProperty("操作时间")
    private String operTime;

    @ApiModelProperty("操作人")
    private String operUser;
}
