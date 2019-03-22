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
    public void projectSubContractorAdd() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("requestSerialCode", "team-add-2019032118-1-0704");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("AsyncHandleResult.Query", data,
            "3311222019030005", "03037a55b99f508d43e732d4d47df2f0");

    }
}
