package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : old3
 * @since : 2019-06-18 16:41
 */
public class XN631754Req {

    @NotBlank(message = "银行卡编号不能为空")
    private String code;

    @NotBlank(message = "项目人员编号不能为空")
    private String workerCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }
}

    
    