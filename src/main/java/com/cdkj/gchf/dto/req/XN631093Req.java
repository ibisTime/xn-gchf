package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631093Req {

    // 免冠照片 （必填）
    @NotBlank(message = "免冠照片不能为空")
    private String pict1;

    public String getPict1() {
        return pict1;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

}
