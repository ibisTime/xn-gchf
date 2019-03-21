package com.cdkj.gchf.govupload;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.ExcelUtil;
import com.cdkj.gchf.gov.GovConnecter;

public class CorpUploader {

    private final static String EXCEL_FILE_PATH = "/Users/silver/Desktop/JM/导入数据/锦鸿.浙江驰凯工贸有限公司年产10万套物联网智能锁具生产线项目/企业信息导入模版.xlsx";

    private final static String EXCEL_FILE_COLUMNS[] = { "corpCode", "corpName",
            "areaCode" };

    private final static String PROJECT_CODE = "3311222019030003";

    private final static String SECRET = "50c0a5bd37c4d9d1153aebf501ed7396";

    public static void main(String args[]) {

        List<Map<String, String>> dataList = ExcelUtil
            .readExcelData(EXCEL_FILE_PATH, EXCEL_FILE_COLUMNS);

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, String> dataMap : dataList) {
                String data = JSONObject.toJSONString(dataMap);
                GovConnecter.getGovData("Corp.Upload", data, PROJECT_CODE,
                    SECRET);
            }
        }

    }

}
