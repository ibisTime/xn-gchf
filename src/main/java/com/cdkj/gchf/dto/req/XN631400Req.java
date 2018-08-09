package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 合同录入
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631400Req {
    // （必填）雇佣编号
    @NotBlank(message = "雇佣编号不能为空")
    private String employCode;

    // （必填）合同照片
    @NotEmpty(message = "合同照片不能为空")
    private List<String> contentPicList;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public List<String> getContentPicList() {
        return contentPicList;
    }

    public void setContentPicList(List<String> contentPicList) {
        this.contentPicList = contentPicList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

}
