package sjjd.doc.line.pojo;

import java.io.Serializable;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public class CustomProtocol<T> implements Serializable {


    private static final long serialVersionUID = 290429819350651974L;
    private String id;
    private String type;
    private T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public CustomProtocol(String id, String type, T data) {
        super();
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public CustomProtocol(String id, String type) {
        super();
        this.id = id;
        this.type = type;

    }

    public CustomProtocol() {

    }

    @Override
    public String toString() {
        return "CustomProtocol [id=" + id + ", type=" + type + ", data=" + data + "]";
    }


}
