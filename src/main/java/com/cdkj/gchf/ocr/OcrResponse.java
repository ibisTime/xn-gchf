package com.cdkj.gchf.ocr;

/**
 * @author old3
 * @title: OcrResponse
 * @description: Ocr识别结果
 * @date 2019-05-16 17:47
 */

public class OcrResponse {

    /**
     * 类型
     */
    private String type;

    /**
     * 正面照图片base64
     */
    private PositiveRes positiveImage;
    /**
     * 反面照base64
     */
    private NegativeRes negativeImage;

    public OcrResponse() {
    }

    public OcrResponse(PositiveRes positiveImage, NegativeRes negativeImage) {
        this.positiveImage = positiveImage;
        this.negativeImage = negativeImage;
    }


    public PositiveRes getPositiveImage() {
        return this.positiveImage;
    }

    public NegativeRes getNegativeImage() {
        return this.negativeImage;
    }

    public void setPositiveImage(PositiveRes positiveImage) {
        this.positiveImage = positiveImage;
    }

    public void setNegativeImage(NegativeRes negativeImage) {
        this.negativeImage = negativeImage;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OcrResponse)) {
            return false;
        }
        final OcrResponse other = (OcrResponse) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$positiveImage = this.getPositiveImage();
        final Object other$positiveImage = other.getPositiveImage();
        if (this$positiveImage == null ? other$positiveImage != null : !this$positiveImage.equals(other$positiveImage)) {
            return false;
        }
        final Object this$negativeImage = this.getNegativeImage();
        final Object other$negativeImage = other.getNegativeImage();
        return this$negativeImage == null ? other$negativeImage == null : this$negativeImage.equals(other$negativeImage);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OcrResponse;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $positiveImage = this.getPositiveImage();
        result = result * PRIME + ($positiveImage == null ? 43 : $positiveImage.hashCode());
        final Object $negativeImage = this.getNegativeImage();
        result = result * PRIME + ($negativeImage == null ? 43 : $negativeImage.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "OcrResponse(positiveImage=" + this.getPositiveImage() + ", negativeImage=" + this.getNegativeImage() + ")";
    }
}
