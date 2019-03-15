package com.cdkj.gchf.gov;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Team {

    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void teamAdd() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("teamName", "泥工");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Team.Add", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }

    @Test
    public void teamUpdate() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("teamSysNo", "100156750");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("teamName", "泥工组");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Team.Update", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }

    @Test
    public void teamQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("projectCode", "3311222019030002");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Team.Query", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
