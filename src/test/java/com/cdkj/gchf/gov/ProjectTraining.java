package com.cdkj.gchf.gov;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class ProjectTraining {

    @Test
    public void projectTrainingAdd() {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("projectCode", "33112220190310002");
        dataMap.put("trainingDate", "2019-02-01");
        dataMap.put("trainingDuration", "5");
        dataMap.put("trainingName", "日常培训");
        dataMap.put("trainingTypeCode", "003001");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectTraining.Add", data,
            "33112220190310002", "24484b262dd63dd584902a266bdbdca0");

        // projecttraining-add-2019032913-4-0004
    }

    @Test
    public void projectTrainingQuery() {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("projectCode", "33112220190310002");
        dataMap.put("trainingDate", "2019-02-01");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectTraining.Query", data,
            "33112220190310002", "24484b262dd63dd584902a266bdbdca0");

        // projecttraining-add-2019032913-4-0004
    }
}
