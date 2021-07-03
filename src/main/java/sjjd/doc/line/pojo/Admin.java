package sjjd.doc.line.pojo;

import java.io.Serializable;

public class Admin implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 管理员名称
     */
    private String adName;

    /**
     * 登录密码
     */
    private String adPassword;

    /**
     * 角色id
     */
    private Integer rId;

    /**
     * 是否删除 0删除 1不删
     */
    private String isDelete;

    /**
     * 用户名
     */
    private String name;

    /**
     * 关联区域id
     */
    private String regionId;

    /**
     * 区域等级 1 省 2市 3县 4机构
     */
    private String regionLevel;

    /**
     * admin
     */
    private static final long serialVersionUID = 1L;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

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
     * 管理员名称
     *
     * @return ad_name 管理员名称
     */
    public String getAdName() {
        return adName;
    }

    /**
     * 管理员名称
     *
     * @param adName 管理员名称
     */
    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    /**
     * 登录密码
     *
     * @return ad_password 登录密码
     */
    public String getAdPassword() {
        return adPassword;
    }

    /**
     * 登录密码
     *
     * @param adPassword 登录密码
     */
    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword == null ? null : adPassword.trim();
    }

    /**
     * 角色id
     *
     * @return r_id 角色id
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 角色id
     *
     * @param rId 角色id
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * 是否删除 0删除 1不删
     *
     * @return is_delete 是否删除 0删除 1不删
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除 0删除 1不删
     *
     * @param isDelete 是否删除 0删除 1不删
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * 用户名
     *
     * @return name 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 关联区域id
     *
     * @return region_id 关联区域id
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * 关联区域id
     *
     * @param regionId 关联区域id
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    /**
     * 区域等级 1 省 2市 3县 4机构
     *
     * @return region_level 区域等级 1 省 2市 3县 4机构
     */
    public String getRegionLevel() {
        return regionLevel;
    }

    /**
     * 区域等级 1 省 2市 3县 4机构
     *
     * @param regionLevel 区域等级 1 省 2市 3县 4机构
     */
    public void setRegionLevel(String regionLevel) {
        this.regionLevel = regionLevel == null ? null : regionLevel.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adName=").append(adName);
        sb.append(", adPassword=").append(adPassword);
        sb.append(", rId=").append(rId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", name=").append(name);
        sb.append(", regionId=").append(regionId);
        sb.append(", regionLevel=").append(regionLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}