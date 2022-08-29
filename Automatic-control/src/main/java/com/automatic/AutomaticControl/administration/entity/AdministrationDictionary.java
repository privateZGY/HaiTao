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
 * 字典表
 * </p>
 *
 * @author zgy
 * @since 2022-06-29
 */
@TableName("administration_dictionary")
@ApiModel(value = "AdministrationDictionary对象", description = "字典表")
public class AdministrationDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典ID")
    @TableId(value = "dirctionary_id", type = IdType.AUTO)
    private Integer dirctionaryId;

    @ApiModelProperty("字典名称")
    private String dirctionaryName;

    @ApiModelProperty("字典编码")
    private String dirctionaryCode;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private String updatePepole;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Integer getDirctionaryId() {
        return dirctionaryId;
    }

    public void setDirctionaryId(Integer dirctionaryId) {
        this.dirctionaryId = dirctionaryId;
    }
    public String getDirctionaryName() {
        return dirctionaryName;
    }

    public void setDirctionaryName(String dirctionaryName) {
        this.dirctionaryName = dirctionaryName;
    }
    public String getDirctionaryCode() {
        return dirctionaryCode;
    }

    public void setDirctionaryCode(String dirctionaryCode) {
        this.dirctionaryCode = dirctionaryCode;
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

    @Override
    public String toString() {
        return "AdministrationDictionary{" +
            "dirctionaryId=" + dirctionaryId +
            ", dirctionaryName=" + dirctionaryName +
            ", dirctionaryCode=" + dirctionaryCode +
            ", deleteFlag=" + deleteFlag +
            ", createTime=" + createTime +
            ", updatePepole=" + updatePepole +
            ", updateTime=" + updateTime +
        "}";
    }
}
