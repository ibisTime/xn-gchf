package com.cdkj.gchf.govupload;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.ExcelUtil;
import com.cdkj.gchf.gov.GovConnecter;

public class ProjectCorpUploader {

    private final static String EXCEL_FILE_PATH = "/Users/silver/Desktop/JM/导入数据/正达。九州新时代/参建单位导入模板.xlsx";

    private final static String EXCEL_FILE_COLUMNS[] = { "projectCode",
            "corpCode", "corpName", "corpType" };

    private final static String PROJECT_CODE = "3311222019030005";

    private final static String SECRET = "03037a55b99f508d43e732d4d47df2f0";

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
