package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 上传人员合同
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631916Req {

    // 项目编码
    @NotBlank
    private String projectCode;

    @NotEmpty
    List<XN631916ReqContract> contractList;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<XN631916ReqContract> getContractList() {
        return contractList;
    }

    public void setContractList(List<XN631916ReqContract> contractList) {
        this.contractList = contractList;
    }

}
