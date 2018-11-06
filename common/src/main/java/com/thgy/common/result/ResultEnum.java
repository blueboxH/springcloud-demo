package com.thgy.common.result;

/**
 * @author box
 * @since 2018/7/4/9:23
 */

public enum ResultEnum {
    UNKNOW_ERROR("-1", "未知错误"),
    SUCCESS("0", "成功"),
    PERMISSION_DENIED_ERROR("200000", "您没有权限执行此操作"),
    PARAMETER_ERROR("200001", "参数错误"),
    INSERT_ERROR("200002", "储存信息失败"),
    UPDATE_ERROR("200003", "修改信息失败"),
    SELECT_ERROR("200004", "没有此条信息"),
    DELETE_ERROR("200005", "删除信息失败"),
    DUPLICATEKEY_ERROR("100005", "数据重复了"),
    PREMISE_STATUS_TASK("200006", "此状态下不能进行此操作"),

//  ===================================================================
//  =====   以上为的错误码为常见的公用的错误码, 抛出异常时可在后面加详细信息  ===
//  ===================================================================
    FILE_UPLOAD_ERROR("100002", "储存文件错误"),
    IDENTITY_NOT_EXISTS("100003", "此身份信息不存在"),
    REFUSE_MODIFY_IDENTITY("100004", "只有审核不通过才能修改身份信息"),
    IDCARD_PICTURE_ERROR("100005", "身份证图片不合要求"),  // 宽高比接近4:3
    IDCARD_OCR_ERROR("100006", "身份证图片识别失败"),
    READ_ASSET_ERROR("100007", "读取资产文件失败"),
    ASSET_FILE_URI_ERROR("100008", "资产文件URI不合法"),
    UNSUPPORTED_FILE_TYPE("100009", "不支持的文件类型"),

    GET_USER_INFOS_ERROR("100016", "获取公共的用户信息出错"),
    GET_USER_LIST_ERROR("100018", "获取用户列表信息失败")
    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
