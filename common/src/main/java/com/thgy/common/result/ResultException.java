package com.thgy.common.result;

/**
 * @author box
 * @since 2018/7/4/9:23
 */

public class ResultException extends RuntimeException {

    private String code;

    /**
     *
     * @param resultEnum 枚举常亮
     */
    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ResultException(ResultEnum resultEnum, String message) {
        super(resultEnum.getMsg() + "【" + message + "】");
        this.code = resultEnum.getCode();
    }

    /**
     * 为了方便直接实例化一个异常
     * @param code
     * @param message
     */
    public ResultException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
