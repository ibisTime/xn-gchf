package com.cdkj.gchf.dto.req;

/**
 * @author old3
 * @title: XN631795Req
 * @description: ocr识别入参
 * @date 2019-05-16 14:13
 */
public class XN631795Req {

    /**
     * side : 身份证方向
     * image : 身份证图片
     */

    private String side;
    private String image;

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
