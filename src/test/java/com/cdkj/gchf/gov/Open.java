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
        dataMap.put("requestSerialCode", "corp-upload-2019032416-4-0007");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("AsyncHandleResult.Query", data,
            "33112220190310002", "24484b262dd63dd584902a266bdbdca0");

    }
}
