package sjjd.doc.line.pojo;

import java.io.Serializable;

public class Role implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String roleName;

    /**
     *
     */
    private String roPermission;

    /**
     * 说明
     */
    private String roExplan;

    /**
     * 上级id
     */
    private String roUpper;

    /**
     * role
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
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * @return ro_permission
     */
    public String getRoPermission() {
        return roPermission;
    }

    /**
     * @param roPermission
     */
    public void setRoPermission(String roPermission) {
        this.roPermission = roPermission == null ? null : roPermission.trim();
    }

    /**
     * 说明
     *
     * @return ro_explan 说明
     */
    public String getRoExplan() {
        return roExplan;
    }

    /**
     * 说明
     *
     * @param roExplan 说明
     */
    public void setRoExplan(String roExplan) {
        this.roExplan = roExplan == null ? null : roExplan.trim();
    }

    /**
     * 上级id
     *
     * @return ro_upper 上级id
     */
    public String getRoUpper() {
        return roUpper;
    }

    /**
     * 上级id
     *
     * @param roUpper 上级id
     */
    public void setRoUpper(String roUpper) {
        this.roUpper = roUpper == null ? null : roUpper.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roPermission=").append(roPermission);
        sb.append(", roExplan=").append(roExplan);
        sb.append(", roUpper=").append(roUpper);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}