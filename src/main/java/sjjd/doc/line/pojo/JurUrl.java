package sjjd.doc.line.pojo;

import java.io.Serializable;

public class JurUrl implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 放过验证的路径
     */
    private String autoUrl;

    /**
     * jur_url
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 放过验证的路径
     *
     * @return auto_url 放过验证的路径
     */
    public String getAutoUrl() {
        return autoUrl;
    }

    /**
     * 放过验证的路径
     *
     * @param autoUrl 放过验证的路径
     */
    public void setAutoUrl(String autoUrl) {
        this.autoUrl = autoUrl == null ? null : autoUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", autoUrl=").append(autoUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}