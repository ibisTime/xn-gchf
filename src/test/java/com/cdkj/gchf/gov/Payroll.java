package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Payroll {
    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void payrollAdd() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("teamSysNo", "100156750");
        dataMap.put("payMonth", "2018-11");

        List<Map<String, String>> detailList = new ArrayList<>();

        Map<String, String> detailMap = new HashMap<String, String>();
        detailMap.put("idCardType", "01");
        detailMap.put("idCardNumber",
            "YUWesLNXSNln9U5qm/CabbuFulAkEcMPWkt7tIAhSKM=");// 413024196804304833
        detailMap.put("days", "21");
        detailMap.put("payRollBankCardNumber",
            "FJfdPSnfyNeRd9frKTIQJWEmjPq/oNT7HLl+jHr4oF0=");// 6217231208010442535
        detailMap.put("payRollBankCode", "102");
        detailMap.put("payRollBankName", "中国工商银行");
        detailMap.put("PayBankCardNumber", "qQUjCYHpGVoKcCIov+pZ5g==");// 381874747048
        detailMap.put("PayBankCode", "104");
        detailMap.put("PayBankName", "中国银行");
        detailMap.put("totalPayAmount", "6500");
        detailMap.put("actualAmount", "6500");
        detailMap.put("isBackPay", "0");
        detailMap.put("balanceDate", "2018-11-15");
        detailMap.put("thirdPayRollCode", "01");

        detailList.add(detailMap);
        dataMap.put("detailList", detailList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Payroll.Add", data);

        // workderattendance-add-2019031415-1-2270
    }

    @Test
    public void payrollQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("date", "2019-03-13");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("projectCode", "3311222019030002");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("WorkerAttendance.Query", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
