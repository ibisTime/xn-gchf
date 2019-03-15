package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class WorkerEntryExit {
    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void workerEntryExitAdd() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("teamSysNo", "100156750");

        List<Map<String, String>> workerList = new ArrayList<>();

        Map<String, String> workerMap = new HashMap<String, String>();
        workerMap.put("idCardType", "01");
        workerMap.put("idCardNumber",
            "YUWesLNXSNln9U5qm/CabbuFulAkEcMPWkt7tIAhSKM=");// 413024196804304833
        workerMap.put("date", "2019-03-13");
        workerMap.put("type", "1");

        workerList.add(workerMap);
        dataMap.put("workerList", workerList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerEntryExit.Add", data);

        // workerentryexit-add-2019031414-1-9279
    }

    @Test
    public void workerEntryExitQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("projectCode", "3311222019030002");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerEntryExit.Query", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
