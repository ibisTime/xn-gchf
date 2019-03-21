package com.cdkj.gchf.gov;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class GovAsync {

    public static AsyncRes queryAsyncHandleResult(String requestSerialCode,
            String projectCode, String secert) {

        String result = null;
        String status = null;

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("requestSerialCode", requestSerialCode);

        String data = JSONObject.toJSON(dataMap).toString();

        String resString = GovConnecter.getGovData("AsyncHandleResult.Query",
            data, projectCode, secert);

        JSONObject resJson = JSONObject.parseObject(resString);
        JSONObject dataJson = resJson.getJSONObject("data");

        if (null != dataJson) {
            result = dataJson.getString("result");
            status = dataJson.getString("status");
        }

        return new AsyncRes(result, status);
    }
}
