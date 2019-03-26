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
        dataMap.put("requestSerialCode", "payroll-add-2019032616-4-0001");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("AsyncHandleResult.Query", data,
            "33112220190310002", "24484b262dd63dd584902a266bdbdca0");

    }
}
