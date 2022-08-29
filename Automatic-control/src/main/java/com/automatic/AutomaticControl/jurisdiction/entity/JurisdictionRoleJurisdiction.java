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
 * 角色权限表
 * </p>
 *
 * @author zgy
 * @since 2022-07-01
 */
@TableName("jurisdiction_role_jurisdiction")
@ApiModel(value = "JurisdictionRoleJurisdiction对象", description = "角色权限表")
public class JurisdictionRoleJurisdiction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色权限ID")
    @TableId(value = "role_jurisdiction_id", type = IdType.AUTO)
    private Integer roleJurisdictionId;

    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("权限ID")
    private Integer jurisdictionId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("修改人")
    private String updatePepole;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Integer getRoleJurisdictionId() {
        return roleJurisdictionId;
    }

    public void setRoleJurisdictionId(Integer roleJurisdictionId) {
        this.roleJurisdictionId = roleJurisdictionId;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(Integer jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
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
        return "JurisdictionRoleJurisdiction{" +
            "roleJurisdictionId=" + roleJurisdictionId +
            ", roleId=" + roleId +
            ", jurisdictionId=" + jurisdictionId +
            ", createTime=" + createTime +
            ", deleteFlag=" + deleteFlag +
            ", updatePepole=" + updatePepole +
            ", updateTime=" + updateTime +
        "}";
    }
}
