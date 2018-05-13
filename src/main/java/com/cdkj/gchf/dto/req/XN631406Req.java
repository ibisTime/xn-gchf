package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查合同
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631406Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8111095288579071200L;

    // （选填）关键字
    private String keyword;

    // （必填）工程编号
    @NotBlank(message = "工程编号不能为空")
    private String projectCode;

    // （选填）更新人
    private String updater;

    // 公司编号
    private List<String> companyCodeList;

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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<String> getCompanyCodeList() {
        return companyCodeList;
    }

    public void setCompanyCodeList(List<String> companyCodeList) {
        this.companyCodeList = companyCodeList;
    }

}
