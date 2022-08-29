package com.automatic.AutomaticControl.commode.controller;

import com.automatic.AutomaticControl.core.readExcel.ReadExcel;
import com.automatic.AutomaticControl.core.result.ResponseResult;
import com.automatic.AutomaticControl.customer.entity.CustomerUser;
import com.automatic.AutomaticControl.customer.service.ICustomerUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author zgy
 * @since 2022-08-28
 */
@Controller
@RequestMapping("/commode/commodityTable")
public class CommodityTableController {

    @Autowired
    private ICustomerUserService iCustomerUserService;

    // 查询用户信息登录
//    @PostMapping("/readExcel")
//    @ApiOperation(value = "读取Excel表格里面的数据", notes = "读取Excel表格里面的数据", httpMethod = "POST", response = CustomerUser.class)
//    public ResponseResult readExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
//        ReadExcel readExcel = new ReadExcel();
//        Map map = readExcel.readExcel(file, request);
//        return  ResponseResult.ok(map);
//    }
}
