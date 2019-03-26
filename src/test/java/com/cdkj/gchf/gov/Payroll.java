package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.AesUtils;

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
        dataMap.put("projectCode", "33112220190310002");
        dataMap.put("corpCode", "913311001484116584");
        dataMap.put("corpName", "正达建设有限公司");
        dataMap.put("teamSysNo", "1500151464");
        dataMap.put("payMonth", "2019-03");

        List<Map<String, String>> detailList = new ArrayList<>();

        Map<String, String> detailMap = new HashMap<String, String>();
        detailMap.put("idCardType", "01");
        detailMap.put("idCardNumber", AesUtils.encrypt("421081199510142315",
            "24484b262dd63dd584902a266bdbdca0"));// 413024196804304833
        detailMap.put("days", "21");
        detailMap.put("workHours", "100");
        detailMap.put("payRollBankCardNumber", AesUtils.encrypt(
            "6217231208010442535", "24484b262dd63dd584902a266bdbdca0"));// 6217231208010442535
        detailMap.put("payRollBankCode", "102");
        detailMap.put("payRollBankName", "中国工商银行");
        detailMap.put("PayBankCardNumber", AesUtils.encrypt("381874747048",
            "24484b262dd63dd584902a266bdbdca0"));// 381874747048
        detailMap.put("PayBankCode", "104");
        detailMap.put("PayBankName", "中国银行");
        detailMap.put("totalPayAmount", "6500");
        detailMap.put("actualAmount", "6500");
        detailMap.put("isBackPay", "0");
        detailMap.put("balanceDate", "2019-03-11");
        detailMap.put("thirdPayRollCode", "01");

        detailList.add(detailMap);
        dataMap.put("detailList", detailList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Payroll.Add", data, "33112220190310002",
            "24484b262dd63dd584902a266bdbdca0");

        // workderattendance-add-2019031415-1-2270
    }

    @Test
    public void payrollQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("payMonth", "2019-03");
        dataMap.put("corpName", "正达建设有限公司");
        dataMap.put("corpCode", "913311001484116584");
        dataMap.put("projectCode", "33112220190310002");
        dataMap.put("teamSysNo", "1500151464");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("Payroll.Query", data, "33112220190310002",
            "24484b262dd63dd584902a266bdbdca0");

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
