package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class WorkerContract {
    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void workerContractAdd() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("projectCode", "3311222019030002");

        List<Map<String, String>> contractList = new ArrayList<>();

        Map<String, String> contractMap = new HashMap<String, String>();
        contractMap.put("corpCode", "91331122MA2A0M9B7N");
        contractMap.put("corpName", "浙江兰芽科技有限公司");
        contractMap.put("idCardType", "01");
        contractMap.put("idCardNumber",
            "YUWesLNXSNln9U5qm/CabbuFulAkEcMPWkt7tIAhSKM=");// 413024196804304833
        contractMap.put("contractPeriodType", "01");
        contractMap.put("startDate", "2018-09-14");
        contractMap.put("endDate", "2019-09-14");

        contractList.add(contractMap);
        dataMap.put("contractList", contractList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerContract.Add", data);

        // workercontract-add-2019031415-1-0001
    }

    @Test
    public void workerContractQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("projectCode", "3311222019030002");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerContract.Query", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
