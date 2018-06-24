package com.cdkj.gchf.dto.res;

public class XN631091Res {
    // 上传凭证
    private String uploadToken;

    public XN631091Res() {
    }

    public XN631091Res(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }
}
