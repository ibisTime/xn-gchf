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

public class ProjectWorkerUploader {

    private final static String EXCEL_FILE_PATH = "/Users/silver/Desktop/JM/导入数据/工艺美校二标段/班组人员导入模板.xlsx";

    private final static String EXCEL_FILE_COLUMNS[] = { "projectCode",
            "corpCode", "corpName", "teamSysNo", "teamName", "workerName",
            "isTeamLeader", "idCardType", "idCardNumber", "workType",
            "workRole", "hasBuyInsurance", "nation", "address", "headImage",
            "politicsType", "cellPhone", "cultureLevelType", "grantOrg" };

    private final static String PROJECT_CODE = "3311222019030004";

    private final static String SECRET = "6307a2c2403b72d08ca978e5dfea1813";

    public static void main(String args[]) {

        List<Map<String, String>> dataList = ExcelUtil
            .readExcelData(EXCEL_FILE_PATH, EXCEL_FILE_COLUMNS);

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, String> dataMap : dataList) {

                String idCardNumber = AesUtils
                    .encrypt(dataMap.get("idCardNumber"), SECRET);
                String cellPhone = dataMap.get("cellPhone");
                if (cellPhone.length() < 11) {
                    cellPhone = String
                        .format("%-11d", Integer.parseInt(cellPhone))
                        .replace(" ", "0");
                }

                Map<String, Object> reqMap = new HashMap<String, Object>();
                reqMap.put("projectCode", dataMap.get("projectCode"));
                reqMap.put("corpCode", dataMap.get("corpCode"));
                reqMap.put("corpName", dataMap.get("corpName"));
                reqMap.put("teamSysNo", dataMap.get("teamSysNo"));
                reqMap.put("teamName", dataMap.get("teamName"));

                List<Map<String, String>> workerList = new ArrayList<>();

                Map<String, String> workerMap = new HashMap<String, String>();
                workerMap.put("workerName", dataMap.get("workerName"));
                workerMap.put("isTeamLeader", dataMap.get("isTeamLeader"));
                workerMap.put("idCardType", dataMap.get("idCardType"));
                workerMap.put("idCardNumber", idCardNumber);
                workerMap.put("workType", dataMap.get("workType"));
                workerMap.put("workRole", dataMap.get("workRole"));
                workerMap.put("hasBuyInsurance",
                    dataMap.get("hasBuyInsurance"));
                workerMap.put("nation", dataMap.get("nation"));
                workerMap.put("address", dataMap.get("address"));
                workerMap.put("headImage", dataMap.get("headImage"));
                workerMap.put("politicsType", dataMap.get("politicsType"));
                workerMap.put("cellPhone", cellPhone);
                workerMap.put("cultureLevelType",
                    dataMap.get("cultureLevelType"));
                workerMap.put("grantOrg", dataMap.get("grantOrg"));

                workerList.add(workerMap);
                reqMap.put("workerList", workerList);

                String data = JSONObject.toJSON(reqMap).toString();
                System.out.println(data);
                GovConnecter.getGovData("ProjectWorker.Add", data, PROJECT_CODE,
                    SECRET);
            }
        }

    }
}
