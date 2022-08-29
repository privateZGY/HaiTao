package com.automatic.AutomaticControl.commode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品详情表
 * </p>
 *
 * @author zgy
 * @since 2022-08-28
 */
@TableName("commode_details")
@ApiModel(value = "CommodeDetails对象", description = "商品详情表")
public class CommodeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品详情ID")
    @TableId(value = "commode_details_id", type = IdType.AUTO)
    private Integer commodeDetailsId;

    @ApiModelProperty("商品ID")
    private Integer commodeId;

    @ApiModelProperty("商品名称")
    private String commodeName;

    @ApiModelProperty("商品价格")
    private BigDecimal commodePrice;

    @ApiModelProperty("品牌")
    private String commodeBrand;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人")
    private String updatePepole;

    public Integer getCommodeDetailsId() {
        return commodeDetailsId;
    }

    public void setCommodeDetailsId(Integer commodeDetailsId) {
        this.commodeDetailsId = commodeDetailsId;
    }
    public Integer getCommodeId() {
        return commodeId;
    }

    public void setCommodeId(Integer commodeId) {
        this.commodeId = commodeId;
    }
    public String getCommodeName() {
        return commodeName;
    }

    public void setCommodeName(String commodeName) {
        this.commodeName = commodeName;
    }
    public BigDecimal getCommodePrice() {
        return commodePrice;
    }

    public void setCommodePrice(BigDecimal commodePrice) {
        this.commodePrice = commodePrice;
    }
    public String getCommodeBrand() {
        return commodeBrand;
    }

    public void setCommodeBrand(String commodeBrand) {
        this.commodeBrand = commodeBrand;
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
        return "CommodeDetails{" +
            "commodeDetailsId=" + commodeDetailsId +
            ", commodeId=" + commodeId +
            ", commodeName=" + commodeName +
            ", commodePrice=" + commodePrice +
            ", commodeBrand=" + commodeBrand +
            ", deleteFlag=" + deleteFlag +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", updatePepole=" + updatePepole +
        "}";
    }
}
