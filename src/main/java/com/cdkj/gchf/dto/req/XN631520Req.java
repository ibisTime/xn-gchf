package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 根据身份证获取务工人员
 * @author: silver 
 * @since: 2018年9月13日 下午4:08:22 
 * @history:
 */
public class XN631520Req {

    // (选填)
    private String idNo;

    // （选填）项目编号列表
    private List<String> projectCodeList;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

}
