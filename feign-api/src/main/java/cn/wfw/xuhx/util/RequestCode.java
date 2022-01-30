package cn.wfw.xuhx.util;

public enum  RequestCode {

    SUCCESS(200,"响应成功！"),
    ERROR(500,"服务器异常！");

    private int code;

    private String message;

    RequestCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
