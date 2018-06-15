package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 录入免冠照片以及特征值
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631411Req {

    // （必填） 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填） 特征值
    @NotBlank(message = "特征值不能为空")
    private String feat;

    // （必填） 免冠照片
    @NotBlank(message = "免冠照片不能为空")
    private String pict1;

    // （必填） 更新人
    @NotBlank(message = " 更新人不能为空")
    private String updater;

    public String getCode() {
        return code;
    }

    public String getFeat() {
        return feat;
    }

    public String getPict1() {
        return pict1;
    }

    public String getUpdater() {
        return updater;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFeat(String feat) {
        this.feat = feat;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
