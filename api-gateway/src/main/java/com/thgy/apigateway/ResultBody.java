package com.thgy.apigateway;


import com.alibaba.fastjson.JSON;
import com.thgy.common.result.ResultEnum;
import com.thgy.common.result.ResultException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * http 请求返回的最外层对象
 *
 * @author box
 * @since 2018/7/4/9:23
 */


public class ResultBody {

    public boolean isSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Object getData() {
        return data;
    }

    public String toJsonString(){
        return JSON.toJSONString(this);
    }

    /**
     * 将错误消息组装成Map返回
     * @return
     */
    public Map<String, Object> toErrorMap(){
        Map<String, Object> map = new LinkedHashMap();
        map.put("success", success);
        map.put("errorCode", errorCode);
        map.put("errorMsg", errorMsg);
        return map;
    }

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误码, 失败才有
     */
    private String errorCode;

    /**
     * 错误消息, 失败才有
     */
    private String errorMsg;

    /**
     * 返回结果
     */
    private Object data;


    /**
     * 静态方法返回成功的结果, 此结果没有具体的数据
     * @return ResultBody
     */
    public static ResultBody success(){
        ResultBody resultBody = new ResultBody();
        resultBody.success = true;
        return resultBody;
    }

    /**
     * 静态方法返回成功的结果, 此结果有具体的数据
     * @param data 具体的返回数据
     * @return ResultBody
     */
    public static ResultBody success(Object data){
        ResultBody resultBody = success();
        resultBody.data = data;
        return resultBody;
    }

    /**
     * 通过直接传入错误码和错误信息构建错误返回结果
     * @param errorCode 错误码
     * @param errorMsg 错误信息
     * @return ResultBody
     */
    public static ResultBody error(String errorCode, String errorMsg){
        ResultBody resultBody = new ResultBody();
        resultBody.errorCode = errorCode;
        resultBody.errorMsg = errorMsg;
        return resultBody;
    }

    public static ResultBody error(ResultEnum resultEnum){
        return error(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static ResultBody error(ResultEnum resultEnum, String message){
        return error(resultEnum.getCode(), resultEnum.getMsg() +
                ":【" + message + "】．");
    }

    /**
     * 通过异常构建返回结果
     *
     * todo: 这种统一异常处理的解决方法并不优雅, 需优化
     * @param throwable 被抛出的异常
     * @return ResultBody
     */
    public static ResultBody error(Throwable throwable){
        if (throwable instanceof ResultException && !((ResultException) throwable).getCode().equals("500")){
            return error(((ResultException) throwable).getCode(),  throwable.getMessage());
        }

        // todo: 日志处理
        return  error(ResultEnum.UNKNOW_ERROR);
    }

}
