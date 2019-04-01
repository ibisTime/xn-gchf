package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631255Req extends APageReq {

    private static final long serialVersionUID = -5911721981138096014L;

    // 统一社会信用代码
    private String corpCode;

    // 用户编号
    @NotBlank
    private String userId;

    // 企业名称
    private String corpName;

    // 企业登记注册类型
    private String corpType;

    // 上传状态
    private String uploadStatus;

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
