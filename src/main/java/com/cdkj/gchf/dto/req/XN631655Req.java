package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631655Req {
    // 用户id
    @NotBlank
    private String userId;

    @NotEmpty
    // 班组编号
    private List<String> codeList;

    // 进场附件列表
    private List<XN631655ReqEntryAttachments> entryAttachments;

    // 退场附件列表
    private List<XN631655ReqExitAttachments> exitAttachments;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public List<XN631655ReqEntryAttachments> getEntryAttachments() {
        return entryAttachments;
    }

    public void setEntryAttachments(
            List<XN631655ReqEntryAttachments> entryAttachments) {
        this.entryAttachments = entryAttachments;
    }

    public List<XN631655ReqExitAttachments> getExitAttachments() {
        return exitAttachments;
    }

    public void setExitAttachments(
            List<XN631655ReqExitAttachments> exitAttachments) {
        this.exitAttachments = exitAttachments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
