package com.cdkj.gchf.gov;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class ProjectSubContractor {

    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void projectSubContractorAdd() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("corpType", "008");
        dataMap.put("entryTime", "2018-08-21 00:00:00");
        dataMap.put("exitTime", "2019-03-01 00:00:00");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectSubContractor.Add", data);

        // subcontractor-add-2019031411-1-0280
    }

    @Test
    public void projectSubContractorUpdate() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("corpType", "008");
        dataMap.put("entryTime", "2018-08-22 00:00:00");
        dataMap.put("exitTime", "2019-03-01 00:00:00");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectSubContractor.Update", data);

        // subcontractor-add-2019031411-1-0280
    }

    @Test
    public void projectSubContractorQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("projectCode", "3311222019030002");
        // dataMap.put("corpCode", "91331122MA2A0M9B7N");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectSubContractor.Query", data);

        // subcontractor-add-2019031411-1-0280
    }
}
