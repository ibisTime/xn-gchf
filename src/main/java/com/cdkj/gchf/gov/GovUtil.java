package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;

public class GovUtil {

    public static <T> Paginable<T> parseGovPage(Integer pageIndex,
            Integer pageSize, String queryString,
            Map<String, String> replaceMap, Class<T> toClazz) {

        JSONObject resJson = JSONObject.parseObject(queryString);
        JSONObject dataJson = resJson.getJSONObject("data");
        List<T> dataList = new ArrayList<>();
        long totalCount = 0L;

        if (null != dataJson) {
            String rowJson = dataJson.getString("rows");

            // 替换返回的字段
            if (null != replaceMap && !replaceMap.isEmpty()) {
                for (String oldString : replaceMap.keySet()) {
                    rowJson = rowJson.replace(oldString,
                        replaceMap.get(oldString));
                }
            }

            dataList = (List<T>) JSONArray.parseArray(rowJson, toClazz);

            totalCount = Long.parseLong(dataJson.getString("totalCount"));
        }

        Paginable<T> page = new Page<T>(pageIndex, pageSize, totalCount);
        page.setList(dataList);

        return page;
    }

    public static String parseRequestSerialCode(String resString) {
        String requestSerialCode = null;

        JSONObject resJson = JSONObject.parseObject(resString);
        JSONObject dataJson = resJson.getJSONObject("data");

        if (null != dataJson) {
            requestSerialCode = dataJson.getString("requestSerialCode");
        }

        return requestSerialCode;
    }
}
