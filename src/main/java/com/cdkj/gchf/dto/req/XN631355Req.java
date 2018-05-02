package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目结束
 * @author: CYL 
 * @since: 2018年4月23日 上午9:50:53 
 * @history:
 */
public class XN631355Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 项目结束时间
    @NotBlank(message = "项目结束时间不能为空")
    private String endDatetime;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
