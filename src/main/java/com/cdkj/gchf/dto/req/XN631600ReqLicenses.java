package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目许可证
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631600ReqLicenses {

    // 工程名称
    @NotBlank
    private String prjName;

    // 施工许可证编号
    @NotBlank
    private String builderLicenseNum;

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getBuilderLicenseNum() {
        return builderLicenseNum;
    }

    public void setBuilderLicenseNum(String builderLicenseNum) {
        this.builderLicenseNum = builderLicenseNum;
    }

}
