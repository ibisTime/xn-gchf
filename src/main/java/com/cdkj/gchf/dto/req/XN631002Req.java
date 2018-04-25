/**
 * @Title XNlh5012Req.java 
 * @Package com.xnjr.moom.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午6:54:44 
 * @version V1.0   
 */
package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午6:54:44 
 * @history:
 */
public class XN631002Req {
    // 编号（必填）
    @NotBlank(message = "编号不能为空")
    private String id;

    // value（必填）
    @NotBlank(message = "value不能为空")
    private String dvalue;

    // 修改人（必填）
    @NotBlank(message = "修改人不能为空")
    private String updater;

    // 备注（选填）
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
