package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查询考勤记录
 * @author: nyc 
 * @since: 2018年4月30日 下午9:11:05 
 * @history:
 */
public class XN631395Req extends APageReq {

    private static final long serialVersionUID = -8582556386723542049L;

    // （选填）关键字
    private String keyword;

    // （选填）项目编号
    private String projectCode;

    // （选填）状态
    private String status;

    // 项目编号List
    private List<String> projectCodeList;

    // 考勤生成日期
    private String createDatetime;

    // 考勤生成月份
    private String createMonth;

    // 考勤生成日期
    private String createDay;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(String createMonth) {
        this.createMonth = createMonth;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

}
