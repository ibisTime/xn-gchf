package com.cdkj.gchf.gov;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Open {

    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void AsyncHandleResultQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("requestSerialCode", "team-add-2019031411-1-0262");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("AsyncHandleResult.Query", data,
            "3311222019030002", "8ec1924ad80f349e71fadf50e75db627");

    }
}
