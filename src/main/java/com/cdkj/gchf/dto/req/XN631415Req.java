package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631415Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 624217987942773996L;

    // （选填）证件类型
    private String idType;

    // （选填）关键字
    private String keyword;

    // （选填）更新人
    private String updater;

    // （选填）公司编号
    private String companyCode;

    // （选填）身份证号
    private String idNo;

    // （选填）公司编号
    private List<String> companyCodeList;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public List<String> getCompanyCodeList() {
        return companyCodeList;
    }

    public void setCompanyCodeList(List<String> companyCodeList) {
        this.companyCodeList = companyCodeList;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

}
