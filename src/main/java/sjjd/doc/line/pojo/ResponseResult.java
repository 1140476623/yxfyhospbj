package sjjd.doc.line.pojo;

import java.io.Serializable;


/**
 * ClassName:ResponseRsult
 * Function: TODO 请简要描述该类的功能及作用
 *
 * @author yang
 * @Date 2018    2018年9月17日		下午3:28:22
 * @see 参考目标类
 * @since JDK1.7
 */
public class ResponseResult<T> implements Serializable {
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since JDK1.7
     */

    private static final long serialVersionUID = 2132298877944999590L;
    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {

    }

    public ResponseResult(Integer state, String message) {
        super();
        this.state = state;
        this.message = message;

    }

    public ResponseResult(Integer state, String message, T data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult [state=" + state + ", message=" + message + ", data=" + data + "]";
    }
}

