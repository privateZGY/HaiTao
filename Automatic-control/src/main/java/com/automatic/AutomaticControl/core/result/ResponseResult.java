package com.automatic.AutomaticControl.core.result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @ProjectName: micro-questions-answers
 * @ClassName: ResponseResult
 * @Description: 统一返回JSON格式数据
 * @Author: xiefan
 * @Date: 2021/11/8 10:51
 *这里是静态编码可以通过类名.变量名来应用例如ResponseResult.success
 */
@Data
@Api("统一返回JSON格式数据类")
public class ResponseResult {

    @ApiModelProperty("响应状态码")
    private Integer code;

    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("响应消息")
    private String message;

    @ApiModelProperty("响应数据")
    private Object data;

    /**
     * 构造函数
     * @param code 响应状态码
     * @param success 是否成功
     * @param message 响应消息
     * @param data 响应数据
     */
    public ResponseResult (Integer code, Boolean success, String message, Object data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功信息
     * @return
     */
    public static ResponseResult ok () {
        ResponseResult responseResult = new ResponseResult(ResultCode.SUCCESS.getCode(), true, ResultCode.SUCCESS.getMessage(), null);
        return responseResult;
    }

    /**
     * 返回成功信息
     * @param resultCode 返回状态码及消息
     * @return
     */
    public static ResponseResult ok (ResultCode resultCode) {
        ResponseResult responseResult = new ResponseResult(resultCode.getCode(), true, resultCode.getMessage(), null);
        return responseResult;
    }

    /**
     * 返回成功信息
     * @param data 返回数据
     * @return
     */
    public static ResponseResult ok (Object data) {
        ResponseResult responseResult = new ResponseResult(ResultCode.SUCCESS.getCode(), true, ResultCode.SUCCESS.getMessage(), data);
        return responseResult;
    }

    /**
     * 返回成功信息
     * @param resultCode 返回状态码及返回消息
     * @param data 返回数据
     * @return
     */
    public static ResponseResult ok (ResultCode resultCode, Object data) {
        ResponseResult responseResult = new ResponseResult(resultCode.getCode(), true, resultCode.getMessage(), data);
        return responseResult;
    }

    /**
     * 返回失败信息
     * @return
     */
    public static ResponseResult error () {
        ResponseResult responseResult = new ResponseResult(ResultCode.FAIL.getCode(), false, ResultCode.FAIL.getMessage(), null);
        return responseResult;
    }

    /**
     * 返回失败信息
     * @param resultCode 返回状态码及消息
     * @return
     */
    public static ResponseResult error (ResultCode resultCode) {
        ResponseResult responseResult = new ResponseResult(resultCode.getCode(), false, resultCode.getMessage(), null);
        return responseResult;
    }

    /**
     * 返回失败信息
     * @param data 返回数据
     * @return
     */
    public static ResponseResult error (Object data) {
        ResponseResult responseResult = new ResponseResult(ResultCode.FAIL.getCode(), false, ResultCode.FAIL.getMessage(), data);
        return responseResult;
    }

    /**
     * 返回失败信息
     * @param resultCode 返回状态码及返回消息
     * @param data 返回数据
     * @return
     */
    public static ResponseResult error (ResultCode resultCode, Object data) {
        ResponseResult responseResult = new ResponseResult(resultCode.getCode(), false, resultCode.getMessage(), data);
        return responseResult;
    }

}
