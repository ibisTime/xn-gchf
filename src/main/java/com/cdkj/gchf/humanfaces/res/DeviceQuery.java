package com.cdkj.gchf.humanfaces.res;

public class DeviceQuery {
    private String result;

    private String code;

    private String msg;

    private DeviceInfo data;

    private Boolean success;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DeviceInfo getData() {
        return data;
    }

    public void setData(DeviceInfo data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((msg == null) ? 0 : msg.hashCode());
        result = prime * result
                + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((success == null) ? 0 : success.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeviceQuery other = (DeviceQuery) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (msg == null) {
            if (other.msg != null)
                return false;
        } else if (!msg.equals(other.msg))
            return false;
        if (result == null) {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        if (success == null) {
            if (other.success != null)
                return false;
        } else if (!success.equals(other.success))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceQuery [result=" + result + ", code=" + code + ", msg="
                + msg + ", data=" + data + ", success=" + success + "]";
    }

}
