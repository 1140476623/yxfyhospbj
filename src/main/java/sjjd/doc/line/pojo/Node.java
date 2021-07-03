package sjjd.doc.line.pojo;

import java.io.Serializable;

public class Node implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String nodeName;

    /**
     *
     */
    private String parentId;

    /**
     *
     */
    private String nodeLink;

    /**
     *
     */
    private String nodePagelogo;

    /**
     * 节点标签
     */
    private String nodeIcon;

    /**
     * node
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
     * @return node_name
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * @return parent_id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * @return node_link
     */
    public String getNodeLink() {
        return nodeLink;
    }

    /**
     * @param nodeLink
     */
    public void setNodeLink(String nodeLink) {
        this.nodeLink = nodeLink == null ? null : nodeLink.trim();
    }

    /**
     * @return node_pagelogo
     */
    public String getNodePagelogo() {
        return nodePagelogo;
    }

    /**
     * @param nodePagelogo
     */
    public void setNodePagelogo(String nodePagelogo) {
        this.nodePagelogo = nodePagelogo == null ? null : nodePagelogo.trim();
    }

    /**
     * 节点标签
     *
     * @return node_icon 节点标签
     */
    public String getNodeIcon() {
        return nodeIcon;
    }

    /**
     * 节点标签
     *
     * @param nodeIcon 节点标签
     */
    public void setNodeIcon(String nodeIcon) {
        this.nodeIcon = nodeIcon == null ? null : nodeIcon.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nodeName=").append(nodeName);
        sb.append(", parentId=").append(parentId);
        sb.append(", nodeLink=").append(nodeLink);
        sb.append(", nodePagelogo=").append(nodePagelogo);
        sb.append(", nodeIcon=").append(nodeIcon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}