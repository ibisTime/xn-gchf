package com.cdkj.gchf.gov;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.AesUtils;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class Worker {

    @Test
    public void workerQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("idCardType", "01");
        dataMap.put("idCardNumber", AesUtils.encrypt("430903198204086948",
                "8f5dd8416dac7175c7f3435de5dd23e7"));

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Worker.Query", data, "3311222019030014",
                "8f5dd8416dac7175c7f3435de5dd23e7");

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }

    @Test
    public void workerCredentialQuery() {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("idCardType", "01");
        dataMap.put("idCardNumber", AesUtils.encrypt("421081199510142315",
            "24484b262dd63dd584902a266bdbdca0"));

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerCredential.Query", data,
            "33112220190310002", "24484b262dd63dd584902a266bdbdca0");

    }
}
