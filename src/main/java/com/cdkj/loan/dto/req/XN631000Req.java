/**
 * @Title XNlh5010Req.java 
 * @Package com.xnjr.moom.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:00:54 
 * @version V1.0   
 */
package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增数据字典
 * @author: nyc 
 * @since: 2018年4月19日 下午2:05:18 
 * @history:
 */
public class XN631000Req {

    // 父key（必填）
    @NotBlank(message = "不为空")
    private String parentKey;

    // key（必填）
    private String dkey;

    // value（必填）
    private String dvalue;

    // 修改人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getDkey() {
        return dkey;
    }

    public void setDkey(String dkey) {
        this.dkey = dkey;
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
