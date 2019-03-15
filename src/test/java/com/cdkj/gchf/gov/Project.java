package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Project {

    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void projectAdd() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("contractorCorpCode", "0");
        dataMap.put("contractorCorpName", "10");
        dataMap.put("name", "91331122MA2A0M9B7N");
        dataMap.put("category", "91331122MA2A0M9B7N");
        dataMap.put("areaCode", "91331122MA2A0M9B7N");
        dataMap.put("prjStatus", "91331122MA2A0M9B7N");
        dataMap.put("name", "91331122MA2A0M9B7N");

        List<Map<String, String>> builderLicensesList = new ArrayList<>();
        Map<String, String> workerMap = new HashMap<String, String>();
        workerMap.put("prjName", "黄明河");
        workerMap.put("builderLicenseNum", "0");
        dataMap.put("builderLicenses", builderLicensesList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Project.Add", data);
    }

    @Test
    public void projectPartUpdate() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("contractorCorpCode", "0");
        dataMap.put("contractorCorpName", "10");
        dataMap.put("name", "91331122MA2A0M9B7N");
        dataMap.put("category", "");
        dataMap.put("areaCode", "");
        dataMap.put("prjStatus", "");
        dataMap.put("name", "");

        List<Map<String, String>> builderLicensesList = new ArrayList<>();
        Map<String, String> workerMap = new HashMap<String, String>();
        workerMap.put("prjName", "黄");
        workerMap.put("builderLicenseNum", "0");
        dataMap.put("builderLicenses", builderLicensesList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Project.PartUpdate", data);
    }

    @Test
    public void projectQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        // dataMap.put("projectCode", "331122201903010001");

        // dataMap.put("contractorCorpCode", "91331122MA2A0M9B7N");
        dataMap.put("contractorCorpName", "浙江兰芽科技有限公司");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Project.Query", data);
    }
}
