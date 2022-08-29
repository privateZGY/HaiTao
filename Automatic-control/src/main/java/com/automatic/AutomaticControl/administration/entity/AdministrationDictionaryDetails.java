package com.automatic.AutomaticControl.administration.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 字典详情表
 * </p>
 *
 * @author zgy
 * @since 2022-06-29
 */
@TableName("administration_dictionary_details")
@ApiModel(value = "AdministrationDictionaryDetails对象", description = "字典详情表")
public class AdministrationDictionaryDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典明细ID")
    @TableId(value = "dictionary_details_id", type = IdType.AUTO)
    private Integer dictionaryDetailsId;

    @ApiModelProperty("字典ID")
    private Integer dictionaryId;

    @ApiModelProperty("类型代码")
    private Integer dictionaryCode;

    @ApiModelProperty("字典值")
    private String dictionaryValue;

    @ApiModelProperty("更新人")
    private String updatePepole;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    public Integer getDictionaryDetailsId() {
        return dictionaryDetailsId;
    }

    public void setDictionaryDetailsId(Integer dictionaryDetailsId) {
        this.dictionaryDetailsId = dictionaryDetailsId;
    }
    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
    public Integer getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(Integer dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }
    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }
    public String getUpdatePepole() {
        return updatePepole;
    }

    public void setUpdatePepole(String updatePepole) {
        this.updatePepole = updatePepole;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AdministrationDictionaryDetails{" +
            "dictionaryDetailsId=" + dictionaryDetailsId +
            ", dictionaryId=" + dictionaryId +
            ", dictionaryCode=" + dictionaryCode +
            ", dictionaryValue=" + dictionaryValue +
            ", updatePepole=" + updatePepole +
            ", updateTime=" + updateTime +
            ", deleteFlag=" + deleteFlag +
            ", createTime=" + createTime +
        "}";
    }
}
