package com.cdkj.gchf.gov;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class CorpUpload {

    @Test
    public void corpUpload() {
        String data = "{\"corpCode\": \"913311001484116584\",\"corpName\": \"正达建设有限公司\",\"areaCode\": \"331122\"}";
        GovConnecter.getGovData("Corp.Upload", JSONObject.toJSONString(data));
    }
}
