package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IQueryLogAO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IQueryLogBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.QueryLog;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EEmploytatus;
import com.cdkj.gchf.enums.EStaffSalaryStatus;

@Service
public class QueryLogAOImpl implements IQueryLogAO {

    @Autowired
    IQueryLogBO queryLogBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IStaffBO staffBO;

    @Override
    public String addQueryLog(String userId, String idNo) {
        userBO.getUser(userId);
        Staff staff = staffBO.getStaffByIdNo(idNo);
        QueryLog data = queryLogBO.isExist(userId, idNo);
        if (data != null) {
            return data.getCode();
        }
        return queryLogBO.save(userId, staff);
    }

    @Override
    public void dropQueryLog(List<String> codeList) {
        for (String code : codeList) {
            QueryLog data = queryLogBO.getQueryLog(code);
            queryLogBO.removeQueryLog(data);
        }
    }

    @Override
    public Paginable<QueryLog> queryQueryLogPage(int start, int limit,
            QueryLog condition) {
        // 获取辖区工程
        User user = userBO.getUser(condition.getUserId());
        Project pCondition = new Project();
        pCondition.setProvince(user.getProvince());
        pCondition.setCity(user.getCity());
        pCondition.setArea(user.getArea());
        List<Project> list = projectBO.queryProject(pCondition);
        List<String> projectCodeList = new ArrayList<String>();
        for (Project project : list) {
            projectCodeList.add(project.getCode());
        }
        Paginable<QueryLog> page = queryLogBO.getPaginable(start, limit,
            condition);
        // 员工所在工程
        Employ eCondition = new Employ();
        List<Employ> employList = null;
        List<Employ> employList2 = null;
        String status = EStaffSalaryStatus.Normal.getCode();

        for (QueryLog queryLog : page.getList()) {
            eCondition.setStaffCode(queryLog.getStaffCode());
            eCondition.setStatus(EEmploytatus.Not_Leave.getCode());
            eCondition.setProjectCodeList(projectCodeList);
            employList = employBO.queryEmployList(eCondition);

            // 辖区内的项目是否有拖欠工资
            eCondition
                .setSalaryStatus(EStaffSalaryStatus.Pay_Portion.getCode());
            employList2 = employBO.queryEmployList(eCondition);
            if (CollectionUtils.isNotEmpty(employList2)) {
                status = EStaffSalaryStatus.Pay_Portion.getCode();

            }
            queryLog.setSalaryStatus(status);
            queryLog.setEmployList(employList);

        }
        return page;
    }

    @Override
    public List<QueryLog> queryQueryLogList(QueryLog condition) {
        // 获取辖区工程
        User user = userBO.getUser(condition.getUserId());
        Project pCondition = new Project();
        pCondition.setProvince(user.getProvince());
        pCondition.setCity(user.getCity());
        pCondition.setArea(user.getArea());
        List<Project> projectList = projectBO.queryProject(pCondition);
        List<String> projectCodeList = new ArrayList<String>();
        for (Project project : projectList) {
            projectCodeList.add(project.getCode());
        }

        List<QueryLog> list = queryLogBO.queryQueryLogList(condition);
        Employ eCondition = new Employ();
        List<Employ> employList = null;
        for (QueryLog queryLog : list) {
            eCondition.setStaffCode(queryLog.getCode());
            eCondition.setStatus(EEmploytatus.Not_Leave.getCode());
            eCondition.setProjectCodeList(projectCodeList);
            employList = employBO.queryEmployList(eCondition);
            queryLog.setEmployList(employList);
        }
        return list;
    }

    @Override
    public QueryLog getQueryLog(String code) {
        QueryLog data = queryLogBO.getQueryLog(code);
        return data;
    }

}
