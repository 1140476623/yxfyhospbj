package sjjd.doc.line.pojo;

import java.io.Serializable;

public class TableResponse<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8307774250341625598L;
    private String code;
    private String msg;
    private String count;
    private String pages;
    private T data;

    public TableResponse() {

    }

    public TableResponse(String code, String msg, String count, String pages, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.pages = pages;
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((count == null) ? 0 : count.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((msg == null) ? 0 : msg.hashCode());
        result = prime * result + ((pages == null) ? 0 : pages.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TableResponse other = (TableResponse) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (count == null) {
            if (other.count != null)
                return false;
        } else if (!count.equals(other.count))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (msg == null) {
            if (other.msg != null)
                return false;
        } else if (!msg.equals(other.msg))
            return false;
        if (pages == null) {
            if (other.pages != null)
                return false;
        } else if (!pages.equals(other.pages))
            return false;
        return true;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TableResponse [code=" + code + ", msg=" + msg + ", count=" + count + ", pages=" + pages + ", data="
                + data + "]";
    }


}
