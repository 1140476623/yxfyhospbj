package sjjd.doc.line.pojo;

import java.io.Serializable;

public class FacRoom implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String rid;

    /**
     *
     */
    private String fid;

    /**
     * fac_room
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
     * @return rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    /**
     * @return fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid
     */
    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rid=").append(rid);
        sb.append(", fid=").append(fid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}