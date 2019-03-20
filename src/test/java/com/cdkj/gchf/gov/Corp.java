package com.cdkj.gchf.gov;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Corp {

    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void corpUpload() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("areaCode", "331122");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Corp.Upload", data, "33112220190310002",
            "24484b262dd63dd584902a266bdbdca0");

        // corp-upload-2019031419-1-1370
    }

}