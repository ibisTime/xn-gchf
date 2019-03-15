package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class WorkerAttendance {
    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void workerAttendanceAdd() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("teamSysNo", "100156750");

        List<Map<String, String>> dataList = new ArrayList<>();

        Map<String, String> subDataList = new HashMap<String, String>();
        subDataList.put("idCardType", "01");
        subDataList.put("idCardNumber",
            "YUWesLNXSNln9U5qm/CabbuFulAkEcMPWkt7tIAhSKM=");// 413024196804304833
        subDataList.put("direction", "01");
        subDataList.put("date", "2019-03-13 07:02:04");

        dataList.add(subDataList);
        dataMap.put("dataList", dataList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerAttendance.Add", data);

        // workderattendance-add-2019031415-1-2270
    }

    @Test
    public void workerAttendanceQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("date", "2019-03-13");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("projectCode", "3311222019030002");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerAttendance.Query", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
