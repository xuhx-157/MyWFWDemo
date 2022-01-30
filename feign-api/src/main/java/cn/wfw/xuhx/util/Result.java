package cn.wfw.xuhx.util;

import java.io.Serializable;

public class Result implements Serializable {

    private RequestCode code;
    private boolean flag;
    private long count;
    private Object data;

    public Result(RequestCode code, boolean flag, Object data,long count) {
        this.code = code;
        this.flag = flag;
        this.count = count;
        this.data = data;
    }

    public Result(RequestCode code,boolean flag, Object data) {
        this.code = code;
        this.flag = flag;
        this.data = data;
    }

    public Result(RequestCode code,  boolean flag) {
        this.code = code;
        this.flag = flag;
    }

    public Result() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public RequestCode getCode() {
        return code;
    }

    public void setCode(RequestCode code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
