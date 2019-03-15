package com.cdkj.gchf.dto.req;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下载
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631431Req {
    // 用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 下载次数
    @NotBlank(message = "下载次数不能为空")
    @Min(value = 0)
    private String download;

    // 反馈下载次数
    @NotBlank(message = "下载次数不能为空")
    @Min(value = 0)
    private String backDownload;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getBackDownload() {
        return backDownload;
    }

    public void setBackDownload(String backDownload) {
        this.backDownload = backDownload;
    }

}
