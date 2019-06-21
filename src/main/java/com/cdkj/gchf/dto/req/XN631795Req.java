package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author old3
 * @title: XN631795Req
 * @description: ocr识别入参
 * @date 2019-05-16 14:13
 */
public class XN631795Req {

    /**
     * positiveImage : 正面照base64
     * negativeImage : 反面照base64
     * userId : 用户id
     * type: 类型 ：0 获取信息 、1 确认信息后录入
     */

    /**
     * 人员code 入参带此参数 则刷新实名制信息
     */
    private String code;

    @NotBlank(message = "正面照不能为空")
    private String positiveImage;
    @NotBlank(message = "反面照不能为空")
    private String negativeImage;
    @NotBlank(message = "用户id不能为空")
    private String userId;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPositiveImage() {
        return positiveImage;
    }

    public void setPositiveImage(String positiveImage) {
        this.positiveImage = positiveImage;
    }

    public String getNegativeImage() {
        return negativeImage;
    }

    public void setNegativeImage(String negativeImage) {
        this.negativeImage = negativeImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
