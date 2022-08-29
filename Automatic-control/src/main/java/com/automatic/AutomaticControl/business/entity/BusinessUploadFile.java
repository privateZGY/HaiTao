package com.automatic.AutomaticControl.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 上传文件表
 * </p>
 *
 * @author zgy
 * @since 2022-06-24
 */
@TableName("business_upload_file")
@ApiModel(value = "BusinessUploadFile对象", description = "上传文件表")
public class BusinessUploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("上传的文件ID")
    @TableId(value = "upload_file_id", type = IdType.AUTO)
    private Integer uploadFileId;

    @ApiModelProperty("上传的文件名")
    private String uploadFileName;

    @ApiModelProperty("上传的文件路径")
    private String uploadFileUrl;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;

    @ApiModelProperty("更新人")
    private String updatePepole;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Integer getUploadFileId() {
        return uploadFileId;
    }

    public void setUploadFileId(Integer uploadFileId) {
        this.uploadFileId = uploadFileId;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public String getUploadFileUrl() {
        return uploadFileUrl;
    }

    public void setUploadFileUrl(String uploadFileUrl) {
        this.uploadFileUrl = uploadFileUrl;
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
        return "BusinessUploadFile{" +
            "uploadFileId=" + uploadFileId +
            ", uploadFileName=" + uploadFileName +
            ", uploadFileUrl=" + uploadFileUrl +
            ", createTime=" + createTime +
            ", deleteFlag=" + deleteFlag +
            ", updatePepole=" + updatePepole +
            ", updateTime=" + updateTime +
        "}";
    }
}
