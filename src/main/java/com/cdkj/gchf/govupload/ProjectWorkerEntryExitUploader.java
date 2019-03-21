package com.cdkj.gchf.govupload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.ExcelUtil;
import com.cdkj.gchf.gov.GovConnecter;

public class ProjectWorkerEntryExitUploader {

    private final static String EXCEL_FILE_PATH = "/Users/silver/Desktop/JM/导入数据/工艺美校二标段/班组人员进退场导入模板.xlsx";

    private final static String EXCEL_FILE_COLUMNS[] = { "projectCode",
            "corpCode", "corpName", "teamSysNo", "idCardType", "idCardNumber",
            "date", "type" };

    private final static String PROJECT_CODE = "3311222019030004";

    private final static String SECRET = "6307a2c2403b72d08ca978e5dfea1813";

    public static void main(String args[]) {

        List<Map<String, String>> dataList = ExcelUtil
            .readExcelData(EXCEL_FILE_PATH, EXCEL_FILE_COLUMNS);

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, String> dataMap : dataList) {

                Map<String, Object> reqMap = new HashMap<String, Object>();
                reqMap.put("projectCode", dataMap.get("projectCode"));
                reqMap.put("corpCode", dataMap.get("corpCode"));
                reqMap.put("corpName", dataMap.get("corpName"));
                reqMap.put("teamSysNo", dataMap.get("teamSysNo"));

                List<Map<String, String>> workerList = new ArrayList<>();

                Map<String, String> workerMap = new HashMap<String, String>();
                workerMap.put("idCardType", dataMap.get("idCardType"));
                workerMap.put("idCardNumber",
                    AesUtils.encrypt(dataMap.get("idCardNumber"), SECRET));
                workerMap.put("date", dataMap.get("date"));
                workerMap.put("type", dataMap.get("type"));

                workerList.add(workerMap);
                reqMap.put("workerList", workerList);

                String data = JSONObject.toJSON(reqMap).toString();

                GovConnecter.getGovData("WorkerEntryExit.Add", data,
                    PROJECT_CODE, SECRET);
            }
        }

    }
}
