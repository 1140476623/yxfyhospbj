package sjjd.doc.line.pojo;

import java.io.Serializable;
import java.util.Date;

public class Voice implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 语音格式
     */
    private String voFormat;

    /**
     * 语音呼叫次数
     */
    private String voNumber;

    /**
     * 呼叫速度
     */
    private String voSpeed;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 操作人员
     */
    private String updateId;

    /**
     * 0为女生 1为男生
     */
    private String voSex;

    /**
     * voice
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
     * 语音格式
     *
     * @return vo_format 语音格式
     */
    public String getVoFormat() {
        return voFormat;
    }

    /**
     * 语音格式
     *
     * @param voFormat 语音格式
     */
    public void setVoFormat(String voFormat) {
        this.voFormat = voFormat == null ? null : voFormat.trim();
    }

    /**
     * 语音呼叫次数
     *
     * @return vo_number 语音呼叫次数
     */
    public String getVoNumber() {
        return voNumber;
    }

    /**
     * 语音呼叫次数
     *
     * @param voNumber 语音呼叫次数
     */
    public void setVoNumber(String voNumber) {
        this.voNumber = voNumber == null ? null : voNumber.trim();
    }

    /**
     * 呼叫速度
     *
     * @return vo_speed 呼叫速度
     */
    public String getVoSpeed() {
        return voSpeed;
    }

    /**
     * 呼叫速度
     *
     * @param voSpeed 呼叫速度
     */
    public void setVoSpeed(String voSpeed) {
        this.voSpeed = voSpeed == null ? null : voSpeed.trim();
    }

    /**
     * 修改时间
     *
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 操作人员
     *
     * @return update_id 操作人员
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 操作人员
     *
     * @param updateId 操作人员
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 0为女生 1为男生
     *
     * @return vo_sex 0为女生 1为男生
     */
    public String getVoSex() {
        return voSex;
    }

    /**
     * 0为女生 1为男生
     *
     * @param voSex 0为女生 1为男生
     */
    public void setVoSex(String voSex) {
        this.voSex = voSex == null ? null : voSex.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", voFormat=").append(voFormat);
        sb.append(", voNumber=").append(voNumber);
        sb.append(", voSpeed=").append(voSpeed);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", voSex=").append(voSex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}