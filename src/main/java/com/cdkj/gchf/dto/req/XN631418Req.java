package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 根据特征值查务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631418Req {

    // (选填)
    private String keyword1;

    // （选填）项目编号列表，手持端使用
    private List<String> projectCodeList;

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

}
