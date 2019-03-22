package com.cdkj.gchf.govupload;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.ExcelUtil;
import com.cdkj.gchf.gov.GovConnecter;

public class TeamMasterUploader extends BaseInfo {

    private final static String EXCEL_FILE_PATH = BASH_EXCEL_FILE_PATH
            + "项目班组导入模板.xlsx";

    private final static String EXCEL_FILE_COLUMNS[] = { "projectCode",
            "corpCode", "corpName", "teamName" };

    public static void main(String args[]) {

        List<Map<String, String>> dataList = ExcelUtil
            .readExcelData(EXCEL_FILE_PATH, EXCEL_FILE_COLUMNS);

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, String> dataMap : dataList) {
                String data = JSONObject.toJSONString(dataMap);

                System.out.println(data);

                GovConnecter.getGovData("Team.Add", data, PROJECT_CODE, SECRET);
            }
        }

    }

}
