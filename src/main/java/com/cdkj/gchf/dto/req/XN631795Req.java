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
     */
    @NotBlank(message = "正面照不能为空")
    private String positiveImage;
    @NotBlank(message = "反面照不能为空")
    private String negativeImage;
    @NotBlank(message = "用户id不能为空")
    private String userId;

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
