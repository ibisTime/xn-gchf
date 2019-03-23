package com.cdkj.gchf.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.AesUtils;

public class ProjectWorker {
    /**
     * 浙江兰芽科技有限公司
     *  
     * @create: Mar 14, 2019 11:37:14 AM silver
     * @history:
     */
    @Test
    public void projectWorkerAdd() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("projectCode", "33112220190310002");
        dataMap.put("corpCode", "913311001484116584");
        dataMap.put("corpName", "正达建设有限公司");
        dataMap.put("teamSysNo", "1500151464");
        dataMap.put("teamName", "钢筋组");

        List<Map<String, String>> workerList = new ArrayList<>();

        Map<String, String> workerMap = new HashMap<String, String>();
        workerMap.put("workerName", "黄明海");
        workerMap.put("isTeamLeader", "0");
        workerMap.put("idCardType", "01");
        workerMap.put("idCardNumber", AesUtils.encrypt("413024196804304833",
            "24484b262dd63dd584902a266bdbdca0"));// 413024196804304833
        workerMap.put("workType", "040");
        workerMap.put("nation", "汉");
        workerMap.put("address", "河南省潢川县张集乡杨集村西赵营组");
        workerMap.put("headImage",
            "http://picture.jm60s.com/15423656452080845.jpg");
        workerMap.put("politicsType", "13");
        workerMap.put("cellPhone", "13735708219");
        workerMap.put("cultureLevelType", "05");
        workerMap.put("grantOrg", "潢川县公安局");
        workerMap.put("workRole", "20");

        workerList.add(workerMap);
        dataMap.put("workerList", workerList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectWorker.Add", data);

        // projectworker-add-2019031414-1-2710
    }

    @Test
    public void projectWorkerUpdate() {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("projectCode", "3311222019030002");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("corpName", "浙江兰芽科技有限公司");
        dataMap.put("teamSysNo", "100156750");
        dataMap.put("teamName", "泥工");

        List<Map<String, String>> workerList = new ArrayList<>();

        Map<String, String> workerMap = new HashMap<String, String>();
        workerMap.put("workerName", "黄明河");
        workerMap.put("isTeamLeader", "0");
        workerMap.put("idCardType", "01");
        workerMap.put("idCardNumber",
            "YUWesLNXSNln9U5qm/CabbuFulAkEcMPWkt7tIAhSKM=");// 413024196804304833
        workerMap.put("workType", "040");
        workerMap.put("nation", "汉");
        workerMap.put("address", "河南省潢川县张集乡杨集村西赵营组");
        workerMap.put("headImage",
            "http://picture.jm60s.com/15423656452080845.jpg");
        workerMap.put("politicsType", "13");
        workerMap.put("cellPhone", "13735708219");
        workerMap.put("cultureLevelType", "05");
        workerMap.put("grantOrg", "潢川县公安局");
        workerMap.put("workRole", "20");

        workerList.add(workerMap);
        dataMap.put("workerList", workerList);

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectWorker.Update", data);

        // projectworker-add-2019031414-1-2710
    }

    @Test
    public void projectWorkerQuery() {

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("pageIndex", "0");
        dataMap.put("pageSize", "10");
        dataMap.put("corpCode", "91331122MA2A0M9B7N");
        dataMap.put("projectCode", "3311222019030002");

        String data = JSONObject.toJSON(dataMap).toString();

        System.out.println(data);

        GovConnecter.getGovData("ProjectWorker.Query", data);

        // team-add-2019031411-1-0262
        // teamSysNo : 100156750
    }
}
