package com.automatic.AutomaticControl.commode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品类型表
 * </p>
 *
 * @author zgy
 * @since 2022-08-28
 */
@TableName("commode_type")
@ApiModel(value = "CommodeType对象", description = "商品类型表")
public class CommodeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(" 商品类型ID")
    @TableId(value = "commode_type_id", type = IdType.AUTO)
    private Integer commodeTypeId;

    @ApiModelProperty("商品名称")
    private String commodeTypeName;

    @ApiModelProperty("商品类型")
    private Boolean commodeType;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人")
    private String updatePepole;

    public Integer getCommodeTypeId() {
        return commodeTypeId;
    }

    public void setCommodeTypeId(Integer commodeTypeId) {
        this.commodeTypeId = commodeTypeId;
    }
    public String getCommodeTypeName() {
        return commodeTypeName;
    }

    public void setCommodeTypeName(String commodeTypeName) {
        this.commodeTypeName = commodeTypeName;
    }
    public Boolean getCommodeType() {
        return commodeType;
    }

    public void setCommodeType(Boolean commodeType) {
        this.commodeType = commodeType;
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
        return "CommodeType{" +
            "commodeTypeId=" + commodeTypeId +
            ", commodeTypeName=" + commodeTypeName +
            ", commodeType=" + commodeType +
            ", deleteFlag=" + deleteFlag +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", updatePepole=" + updatePepole +
        "}";
    }
}
