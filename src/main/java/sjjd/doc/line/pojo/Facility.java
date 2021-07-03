package sjjd.doc.line.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class Facility implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    @ApiModelProperty("mac")
    private String facMacid;

    /**
     * 加密字符串
     */
    @ApiModelProperty("加密字符串")
    private String facEncrypt;

    /**
     * 解密字符串
     */
    @ApiModelProperty("解密字符串")
    private String facDecode;

    /**
     * 设备版本号
     */
    @ApiModelProperty("设备版本号")
    private String facNumber;

    /**
     * 设备状态 0下线 1上线
     */
    @ApiModelProperty("设备状态 0下线 1上线")
    private String facState;

    /**
     * 是否禁用 0禁用 1启用
     */
    @ApiModelProperty("是否禁用 0禁用 1启用")
    private String facManage;

    /**
     *
     */
    @ApiModelProperty("")
    private String facClientid;

    /**
     * 窗口id
     */
    @ApiModelProperty("窗口id")
    private String facRoom;

    /**
     * 楼层
     */
    @ApiModelProperty("楼层")
    private String facFloor;

    /**
     * 科室
     */
    @ApiModelProperty("科室")
    private String facDid;

    /**
     * 是否注册
     */
    @ApiModelProperty("是否注册")
    private String facRegister;

    /**
     * 设备ip
     */
    @ApiModelProperty("设备ip")
    private String facIp;

    /**
     * 1-排号机 2-呼叫器  3-门牌屏  4-综合屏  5-签到机  6-评价器  7-排班机 8.综合屏二
     */
    @ApiModelProperty("1-排号机 2-呼叫器  3-门牌屏  4-综合屏  5-签到机  6-评价器  7-排班机 8.综合屏二")
    private String facType;

    /**
     * 系统类型 0-安卓 1-windows
     */
    @ApiModelProperty("系统类型 0-安卓 1-windows")
    private String sysType;

    /**
     * 截屏图链接
     */
    @ApiModelProperty("截屏图链接")
    private String shotLink;

    /**
     * 设备地址
     */
    @ApiModelProperty("设备地址")
    private String facAddres;

    /**
     * 语音格式id
     */
    @ApiModelProperty("语音格式id")
    private String vId;

    /**
     * 是否启用语音格式 0否 1是
     */
    @ApiModelProperty("是否启用语音格式 0否 1是")
    private String isVoice;

    /**
     * 声音大小
     */
    @ApiModelProperty("声音大小")
    private String voiceSize;

    /**
     * 显示内容切换 1-排队信息 2-推送节目
     */
    @ApiModelProperty("显示内容切换 1-排队信息 2-推送节目")
    private String coSwitch;

    /**
     * 设备区域
     */
    @ApiModelProperty("设备区域")
    private String facArea;

    /**
     * 开机时间
     */
    @ApiModelProperty("开机时间")
    private String uptime;

    /**
     * 关机时间
     */
    @ApiModelProperty("关机时间")
    private String offtime;

    /**
     * 机构id
     */
    @ApiModelProperty("机构id")
    private String orgCode;

    /**
     *
     */
    @ApiModelProperty("设备编码")
    private String temp;

    /**
     * 版本信息
     */
    private String facVersion;

    private List<PrintInfo> printInfo;


    public List<PrintInfo> getPrintInfo() {
        return printInfo;
    }

    public void setPrintInfo(List<PrintInfo> printInfo) {
        this.printInfo = printInfo;
    }

    /**
     * facility
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
     * @return fac_macid
     */
    public String getFacMacid() {
        return facMacid;
    }

    /**
     * @param facMacid
     */
    public void setFacMacid(String facMacid) {
        this.facMacid = facMacid == null ? null : facMacid.trim();
    }

    /**
     * 加密字符串
     *
     * @return fac_encrypt 加密字符串
     */
    public String getFacEncrypt() {
        return facEncrypt;
    }

    /**
     * 加密字符串
     *
     * @param facEncrypt 加密字符串
     */
    public void setFacEncrypt(String facEncrypt) {
        this.facEncrypt = facEncrypt == null ? null : facEncrypt.trim();
    }

    /**
     * 解密字符串
     *
     * @return fac_decode 解密字符串
     */
    public String getFacDecode() {
        return facDecode;
    }

    /**
     * 解密字符串
     *
     * @param facDecode 解密字符串
     */
    public void setFacDecode(String facDecode) {
        this.facDecode = facDecode == null ? null : facDecode.trim();
    }

    /**
     * 设备版本号
     *
     * @return fac_number 设备版本号
     */
    public String getFacNumber() {
        return facNumber;
    }

    /**
     * 设备版本号
     *
     * @param facNumber 设备版本号
     */
    public void setFacNumber(String facNumber) {
        this.facNumber = facNumber == null ? null : facNumber.trim();
    }

    /**
     * 设备状态 0下线 1上线
     *
     * @return fac_state 设备状态 0下线 1上线
     */
    public String getFacState() {
        return facState;
    }

    /**
     * 设备状态 0下线 1上线
     *
     * @param facState 设备状态 0下线 1上线
     */
    public void setFacState(String facState) {
        this.facState = facState == null ? null : facState.trim();
    }

    /**
     * 是否禁用 0禁用 1启用
     *
     * @return fac_manage 是否禁用 0禁用 1启用
     */
    public String getFacManage() {
        return facManage;
    }

    /**
     * 是否禁用 0禁用 1启用
     *
     * @param facManage 是否禁用 0禁用 1启用
     */
    public void setFacManage(String facManage) {
        this.facManage = facManage == null ? null : facManage.trim();
    }

    /**
     * @return fac_clientId
     */
    public String getFacClientid() {
        return facClientid;
    }

    /**
     * @param facClientid
     */
    public void setFacClientid(String facClientid) {
        this.facClientid = facClientid == null ? null : facClientid.trim();
    }

    /**
     * 窗口id
     *
     * @return fac_room 窗口id
     */
    public String getFacRoom() {
        return facRoom;
    }

    /**
     * 窗口id
     *
     * @param facRoom 窗口id
     */
    public void setFacRoom(String facRoom) {
        this.facRoom = facRoom == null ? null : facRoom.trim();
    }

    /**
     * 楼层
     *
     * @return fac_floor 楼层
     */
    public String getFacFloor() {
        return facFloor;
    }

    /**
     * 楼层
     *
     * @param facFloor 楼层
     */
    public void setFacFloor(String facFloor) {
        this.facFloor = facFloor == null ? null : facFloor.trim();
    }

    /**
     * 科室
     *
     * @return fac_did 科室
     */
    public String getFacDid() {
        return facDid;
    }

    /**
     * 科室
     *
     * @param facDid 科室
     */
    public void setFacDid(String facDid) {
        this.facDid = facDid == null ? null : facDid.trim();
    }

    /**
     * 是否注册
     *
     * @return fac_register 是否注册
     */
    public String getFacRegister() {
        return facRegister;
    }

    /**
     * 是否注册
     *
     * @param facRegister 是否注册
     */
    public void setFacRegister(String facRegister) {
        this.facRegister = facRegister == null ? null : facRegister.trim();
    }

    /**
     * 设备ip
     *
     * @return fac_ip 设备ip
     */
    public String getFacIp() {
        return facIp;
    }

    /**
     * 设备ip
     *
     * @param facIp 设备ip
     */
    public void setFacIp(String facIp) {
        this.facIp = facIp == null ? null : facIp.trim();
    }

    /**
     * 1-排号机 2-呼叫器  3-门牌屏  4-综合屏  5-签到机  6-评价器  7-排班机 8.综合屏二
     *
     * @return fac_type 1-排号机 2-呼叫器  3-门牌屏  4-综合屏  5-签到机  6-评价器  7-排班机 8.综合屏二
     */
    public String getFacType() {
        return facType;
    }

    /**
     * 1-排号机 2-呼叫器  3-门牌屏  4-综合屏  5-签到机  6-评价器  7-排班机 8.综合屏二
     *
     * @param facType 1-排号机 2-呼叫器  3-门牌屏  4-综合屏  5-签到机  6-评价器  7-排班机 8.综合屏二
     */
    public void setFacType(String facType) {
        this.facType = facType == null ? null : facType.trim();
    }

    /**
     * 系统类型 0-安卓 1-windows
     *
     * @return sys_type 系统类型 0-安卓 1-windows
     */
    public String getSysType() {
        return sysType;
    }

    /**
     * 系统类型 0-安卓 1-windows
     *
     * @param sysType 系统类型 0-安卓 1-windows
     */
    public void setSysType(String sysType) {
        this.sysType = sysType == null ? null : sysType.trim();
    }

    /**
     * 截屏图链接
     *
     * @return shot_link 截屏图链接
     */
    public String getShotLink() {
        return shotLink;
    }

    /**
     * 截屏图链接
     *
     * @param shotLink 截屏图链接
     */
    public void setShotLink(String shotLink) {
        this.shotLink = shotLink == null ? null : shotLink.trim();
    }

    /**
     * 设备地址
     *
     * @return fac_addres 设备地址
     */
    public String getFacAddres() {
        return facAddres;
    }

    /**
     * 设备地址
     *
     * @param facAddres 设备地址
     */
    public void setFacAddres(String facAddres) {
        this.facAddres = facAddres == null ? null : facAddres.trim();
    }

    /**
     * 语音格式id
     *
     * @return v_id 语音格式id
     */
    public String getvId() {
        return vId;
    }

    /**
     * 语音格式id
     *
     * @param vId 语音格式id
     */
    public void setvId(String vId) {
        this.vId = vId == null ? null : vId.trim();
    }

    /**
     * 是否启用语音格式 0否 1是
     *
     * @return is_voice 是否启用语音格式 0否 1是
     */
    public String getIsVoice() {
        return isVoice;
    }

    /**
     * 是否启用语音格式 0否 1是
     *
     * @param isVoice 是否启用语音格式 0否 1是
     */
    public void setIsVoice(String isVoice) {
        this.isVoice = isVoice == null ? null : isVoice.trim();
    }

    /**
     * 声音大小
     *
     * @return voice_size 声音大小
     */
    public String getVoiceSize() {
        return voiceSize;
    }

    /**
     * 声音大小
     *
     * @param voiceSize 声音大小
     */
    public void setVoiceSize(String voiceSize) {
        this.voiceSize = voiceSize == null ? null : voiceSize.trim();
    }

    /**
     * 显示内容切换 1-排队信息 2-推送节目
     *
     * @return co_switch 显示内容切换 1-排队信息 2-推送节目
     */
    public String getCoSwitch() {
        return coSwitch;
    }

    /**
     * 显示内容切换 1-排队信息 2-推送节目
     *
     * @param coSwitch 显示内容切换 1-排队信息 2-推送节目
     */
    public void setCoSwitch(String coSwitch) {
        this.coSwitch = coSwitch == null ? null : coSwitch.trim();
    }

    /**
     * 设备区域
     *
     * @return fac_area 设备区域
     */
    public String getFacArea() {
        return facArea;
    }

    /**
     * 设备区域
     *
     * @param facArea 设备区域
     */
    public void setFacArea(String facArea) {
        this.facArea = facArea == null ? null : facArea.trim();
    }

    /**
     * 开机时间
     *
     * @return uptime 开机时间
     */
    public String getUptime() {
        return uptime;
    }

    /**
     * 开机时间
     *
     * @param uptime 开机时间
     */
    public void setUptime(String uptime) {
        this.uptime = uptime == null ? null : uptime.trim();
    }

    /**
     * 关机时间
     *
     * @return offtime 关机时间
     */
    public String getOfftime() {
        return offtime;
    }

    /**
     * 关机时间
     *
     * @param offtime 关机时间
     */
    public void setOfftime(String offtime) {
        this.offtime = offtime == null ? null : offtime.trim();
    }

    /**
     * 机构id
     *
     * @return org_code 机构id
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构id
     *
     * @param orgCode 机构id
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * @return temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     * @param temp
     */
    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    /**
     * 版本信息
     *
     * @return fac_version 版本信息
     */
    public String getFacVersion() {
        return facVersion;
    }

    /**
     * 版本信息
     *
     * @param facVersion 版本信息
     */
    public void setFacVersion(String facVersion) {
        this.facVersion = facVersion == null ? null : facVersion.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", facMacid=").append(facMacid);
        sb.append(", facEncrypt=").append(facEncrypt);
        sb.append(", facDecode=").append(facDecode);
        sb.append(", facNumber=").append(facNumber);
        sb.append(", facState=").append(facState);
        sb.append(", facManage=").append(facManage);
        sb.append(", facClientid=").append(facClientid);
        sb.append(", facRoom=").append(facRoom);
        sb.append(", facFloor=").append(facFloor);
        sb.append(", facDid=").append(facDid);
        sb.append(", facRegister=").append(facRegister);
        sb.append(", facIp=").append(facIp);
        sb.append(", facType=").append(facType);
        sb.append(", sysType=").append(sysType);
        sb.append(", shotLink=").append(shotLink);
        sb.append(", facAddres=").append(facAddres);
        sb.append(", vId=").append(vId);
        sb.append(", isVoice=").append(isVoice);
        sb.append(", voiceSize=").append(voiceSize);
        sb.append(", coSwitch=").append(coSwitch);
        sb.append(", facArea=").append(facArea);
        sb.append(", uptime=").append(uptime);
        sb.append(", offtime=").append(offtime);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", temp=").append(temp);
        sb.append(", facVersion=").append(facVersion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}