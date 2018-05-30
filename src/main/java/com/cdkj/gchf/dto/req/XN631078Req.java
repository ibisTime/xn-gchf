package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修该头像
 * @author: nyc 
 * @since: 2018年5月30日 上午11:28:39 
 * @history:
 */
public class XN631078Req {

    // 用户编号(必填)
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // 头像(必填)
    @NotBlank(message = "头像不能为空")
    private String photo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
