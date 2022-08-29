package com.automatic.AutomaticControl.business.controller;

import com.automatic.AutomaticControl.business.entity.BusinessUploadFile;
import com.automatic.AutomaticControl.business.service.IBusinessUploadFileService;
import com.automatic.AutomaticControl.core.result.ResponseResult;
import com.automatic.AutomaticControl.core.uploadImg.UploadImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 上传文件表 前端控制器
 * </p>
 *
 * @author zgy
 * @since 2022-06-24
 */
@RestController
@RequestMapping("/business/businessUploadFile")
public class BusinessUploadFileController {

    @Autowired
    private IBusinessUploadFileService iBusinessUploadFileService;

    @PostMapping("/upload")
    public ResponseResult upload (BusinessUploadFile businessUploadFile, @RequestParam("file")MultipartFile[] file, HttpServletRequest request) throws IOException {
        UploadImg uploadImg = new UploadImg();
        Map map = uploadImg.files(file, request);
        businessUploadFile.setUploadFileUrl(map.get("urls1").toString());
        businessUploadFile.setUploadFileName(map.get("urls2").toString());
        Boolean addUpload = iBusinessUploadFileService.save(businessUploadFile);
        return ResponseResult.ok(businessUploadFile);
    }

}
