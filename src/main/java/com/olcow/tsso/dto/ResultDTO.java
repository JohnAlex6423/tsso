package com.olcow.tsso.dto;

import com.olcow.tsso.constant.ReturnCode;

public class ResultDTO {

    /**
     * 请求状态
     */
    private String code;

    /**
     * 结果信息
     */
    private String message;

    /**
     * 请求结果
     */
    private Object data;

    public ResultDTO() {
    }

    public ResultDTO(ReturnCode returnCode, Object data) {
        this.code = returnCode.getCode();
        this.message = returnCode.getMsg();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
