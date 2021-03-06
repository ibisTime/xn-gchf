package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;

public class GovUtil {

    /**
     * 解析分页查询结果
     * @param pageIndex
     * @param pageSize
     * @param queryString
     * @param replaceMap
     * @param toClazz
     * @return 
     * @create: Mar 22, 2019 3:36:09 PM silver
     * @history:
     */
    public static <T> Paginable<T> parseGovPage(Integer pageIndex,
            Integer pageSize, String queryString,
            Map<String, String> replaceMap, Class<T> toClazz) {

        queryString = queryString.replace("null", "\"\"");
        JSONObject resJson = JSONObject.parseObject(queryString);
        JSONObject dataJson = resJson.getJSONObject("data");
        List<T> dataList = new ArrayList<>();
        long totalCount = 0L;

        if (null != dataJson) {

            String rowJson = dataJson.getString("rows");
            Boolean payRollFlag = false;

            if (StringUtils.isEmpty(rowJson)) {
                rowJson = "[" + dataJson.toJSONString() + "]";
                payRollFlag = true;
            }

            if (null != replaceMap && !replaceMap.isEmpty()) {
                for (String oldString : replaceMap.keySet()) {
                    rowJson = rowJson.replace(oldString,
                        replaceMap.get(oldString));
                }
            }

            dataList = (List<T>) JSONArray.parseArray(rowJson, toClazz);

            if (payRollFlag) {
                totalCount = dataList.size();
            } else {
                totalCount = Long.parseLong(dataJson.getString("totalCount"));
            }
        }

        Paginable<T> page = new Page<T>(pageIndex, pageSize, totalCount);
        page.setList(dataList);

        return page;
    }

    /**
     * 查询异步接口结果
     * @param requestSerialCode
     * @param projectCode
     * @param secert
     * @return 
     * @create: Mar 22, 2019 3:36:40 PM silver
     * @history:
     */
    public static AsyncRes queryAsyncHandleResult(String requestSerialCode,
            String projectCode, String secert) {

        String code = null;
        String result = null;
        String status = null;
        String message = null;

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

        code = resJson.getString("code");
        message = resJson.getString("message");

        return new AsyncRes(code, result, status, message);
    }

    /**
     * 解析异步查询结果
     * @param resString
     * @return 
     * @create: Mar 22, 2019 3:36:23 PM silver
     * @history:
     */
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
