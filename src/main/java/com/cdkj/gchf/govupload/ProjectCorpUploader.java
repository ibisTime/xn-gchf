package com.cdkj.gchf.govupload;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.ExcelUtil;
import com.cdkj.gchf.gov.GovConnecter;

public class ProjectCorpUploader {

    private final static String EXCEL_FILE_PATH = "/Users/silver/Desktop/JM/导入数据/锦鸿建设.兰芽科技项目/参建单位导入模板.xlsx";

    private final static String EXCEL_FILE_COLUMNS[] = { "projectCode",
            "corpCode", "corpName", "corpType" };

    private final static String PROJECT_CODE = "3311222019030002";

    private final static String SECRET = "8ec1924ad80f349e71fadf50e75db627";

    public static void main(String args[]) {

        List<Map<String, String>> dataList = ExcelUtil
            .readExcelData(EXCEL_FILE_PATH, EXCEL_FILE_COLUMNS);

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, String> dataMap : dataList) {
                String data = JSONObject.toJSONString(dataMap);
                GovConnecter.getGovData("ProjectSubContractor.Add", data,
                    PROJECT_CODE, SECRET);
            }
        }

    }

}
