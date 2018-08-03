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

    // （选填）雇佣编号
    private String employCode;

    // （选填）关键字
    private String keyword;

    // （必填）工程编号
    @NotBlank(message = "工程编号不能为空")
    private String projectCode;

    // （选填）更新人
    private String updater;

    // 公司编号
    private List<String> projectCodeList;

    // 用户类型
    private String kind;

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

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

}
