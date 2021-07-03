package sjjd.doc.line.Exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"status", "message", "exMsg", "data"})
public class OutputJson {
    /**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //状态码
    private int state;
    //必要的提示信息
    private String message;
    //报错提示
    private String exMsg;
    //业务数据
    private Object data;

    public OutputJson(int state, String message, String exMsg, Object data) {
        this.exMsg = exMsg;
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return state;
    }

    public void setStatus(int status) {
        this.state = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public String toString() {
        if (null == this.data) {
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}
