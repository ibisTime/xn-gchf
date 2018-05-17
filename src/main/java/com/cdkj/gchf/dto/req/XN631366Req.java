package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查公司编号
 * @author: nyc 
 * @since: 2018年4月27日 下午9:40:33 
 * @history:
 */
public class XN631366Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3085238015622686279L;

    // （选填） 公司编号
    private String companyCode;

    // （选填） 关键字,关联工程名称,户名，开户行，
    private String keyword;

    // （选填） 项目编号
    private String projectCode;

    // （选填）状态
    private String status;

    // 用户类型
    private String kind;

    // 编号List
    private List<String> projectCodeList;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

}
