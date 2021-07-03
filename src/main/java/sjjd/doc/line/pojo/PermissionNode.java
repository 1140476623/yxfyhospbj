package sjjd.doc.line.pojo;

import java.io.Serializable;

public class PermissionNode implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer roleId;

    /**
     *
     */
    private Integer nodeId;

    /**
     * permission_node
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
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return node_id
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", nodeId=").append(nodeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}