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
 * 商品表
 * </p>
 *
 * @author zgy
 * @since 2022-08-28
 */
@TableName("commodity_table")
@ApiModel(value = "CommodityTable对象", description = "商品表")
public class CommodityTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品ID")
    @TableId(value = "commodity_id", type = IdType.AUTO)
    private Integer commodityId;

    @ApiModelProperty("商品名称")
    private String commodityName;

    @ApiModelProperty("商品信息")
    private String commodityInformation;

    @ApiModelProperty("商品价格")
    private BigDecimal commodityPrice;

    @ApiModelProperty("销售量")
    private Long commoditySales;

    @ApiModelProperty("商品图片")
    private String commodityImage;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private String updatePepole;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getCommodityInformation() {
        return commodityInformation;
    }

    public void setCommodityInformation(String commodityInformation) {
        this.commodityInformation = commodityInformation;
    }
    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }
    public Long getCommoditySales() {
        return commoditySales;
    }

    public void setCommoditySales(Long commoditySales) {
        this.commoditySales = commoditySales;
    }
    public String getCommodityImage() {
        return commodityImage;
    }

    public void setCommodityImage(String commodityImage) {
        this.commodityImage = commodityImage;
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
        return "CommodityTable{" +
            "commodityId=" + commodityId +
            ", commodityName=" + commodityName +
            ", commodityInformation=" + commodityInformation +
            ", commodityPrice=" + commodityPrice +
            ", commoditySales=" + commoditySales +
            ", commodityImage=" + commodityImage +
            ", deleteFlag=" + deleteFlag +
            ", createTime=" + createTime +
            ", updatePepole=" + updatePepole +
            ", updateTime=" + updateTime +
        "}";
    }
}
