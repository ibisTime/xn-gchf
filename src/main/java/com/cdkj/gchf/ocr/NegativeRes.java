package com.cdkj.gchf.ocr;

/**
 * @author old3
 * @title: NegativeRes
 * @description: 反面照响应
 * @date 2019-05-16 14:27
 */
public class NegativeRes {


    /**
     * config_str : 方向 {"side":"back"}
     * end_date : 有效期截止时间 20240321
     * issue : 签发机关 三门县公安局
     * request_id : 20190516141140_f521e7813ca9a4d3c579eb8d45b90490
     * start_date : 有效期开始时间 20140321
     * success : true
     */

    private String config_str;

    private String end_date;

    private String issue;

    private String request_id;

    private String start_date;

    private boolean success;

    public String getConfig_str() {
        return config_str;
    }

    public void setConfig_str(String config_str) {
        this.config_str = config_str;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
