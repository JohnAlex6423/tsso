package com.olcow.tsso.dto;

public class ResultDTO {

    /**
     * 请求状态
     */
    private boolean status;

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

    public ResultDTO(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
