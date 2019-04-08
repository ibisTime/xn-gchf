package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName:  XN631647Req   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月19日 下午10:33:38     
 * @Copyright:
 */
public class XN631647Req extends AListReq {

    private static final long serialVersionUID = 9192316630188356600L;

    private String projectCode;

    private String projectName;

    private String corpType;

    private String uploadStatus;

    @NotBlank
    private String userId;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

}
