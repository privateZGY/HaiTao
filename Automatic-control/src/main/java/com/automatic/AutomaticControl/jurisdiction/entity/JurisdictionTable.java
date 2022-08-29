package com.automatic.AutomaticControl.jurisdiction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author zgy
 * @since 2022-07-01
 */
@TableName("jurisdiction_table")
@ApiModel(value = "JurisdictionTable对象", description = "权限表")
public class JurisdictionTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限ID")
    @TableId(value = "jurisdiction_id", type = IdType.AUTO)
    private Integer jurisdictionId;

    @ApiModelProperty("权限名称")
    private String jurisdictionName;

    @ApiModelProperty("权限图标")
    private String icon;

    @ApiModelProperty("权限链接（权限链接url就是controller里面的链接）")
    private String url;

    @ApiModelProperty("权限标志(权限标志就是定义链接后面自定义的一个标志MessageFormat.format(restPermissionString, \"jurisdiction\"))")
    private String jurisdictionSign;

    @ApiModelProperty("权限类型【0.目录，1.子菜单，2.按钮，3.接口权限】")
    private Boolean jurisdictionType;

    @ApiModelProperty("权限排序")
    private Integer jurisdictionSort;

    @ApiModelProperty("权限父ID")
    private Integer jurisdictionMaxId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人")
    private String updatePepole;

    public Integer getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(Integer jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }
    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getJurisdictionSign() {
        return jurisdictionSign;
    }

    public void setJurisdictionSign(String jurisdictionSign) {
        this.jurisdictionSign = jurisdictionSign;
    }
    public Boolean getJurisdictionType() {
        return jurisdictionType;
    }

    public void setJurisdictionType(Boolean jurisdictionType) {
        this.jurisdictionType = jurisdictionType;
    }
    public Integer getJurisdictionSort() {
        return jurisdictionSort;
    }

    public void setJurisdictionSort(Integer jurisdictionSort) {
        this.jurisdictionSort = jurisdictionSort;
    }
    public Integer getJurisdictionMaxId() {
        return jurisdictionMaxId;
    }

    public void setJurisdictionMaxId(Integer jurisdictionMaxId) {
        this.jurisdictionMaxId = jurisdictionMaxId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdatePepole() {
        return updatePepole;
    }

    public void setUpdatePepole(String updatePepole) {
        this.updatePepole = updatePepole;
    }

    @Override
    public String toString() {
        return "JurisdictionTable{" +
            "jurisdictionId=" + jurisdictionId +
            ", jurisdictionName=" + jurisdictionName +
            ", icon=" + icon +
            ", url=" + url +
            ", jurisdictionSign=" + jurisdictionSign +
            ", jurisdictionType=" + jurisdictionType +
            ", jurisdictionSort=" + jurisdictionSort +
            ", jurisdictionMaxId=" + jurisdictionMaxId +
            ", createTime=" + createTime +
            ", deleteFlag=" + deleteFlag +
            ", updateTime=" + updateTime +
            ", updatePepole=" + updatePepole +
        "}";
    }
}
