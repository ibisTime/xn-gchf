package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.ESalaryLogType;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class SalaryLogAOImpl implements ISalaryLogAO {

    @Autowired
    private ISalaryLogBO salaryLogBO;

    @Autowired
    private ISalaryBO salaryBO;

    @Autowired
    private IStaffBO staffBO;

    @Override
    public String addSalaryLog(String salaryCode, String handler,
            String handleNote, List<String> handlePicList) {

        Salary salary = salaryBO.getSalary(salaryCode);

        StringBuffer stringBuffer = new StringBuffer();
        if (CollectionUtils.isNotEmpty(handlePicList)) {
            for (String handlePic : handlePicList) {
                stringBuffer.append(",").append(handlePic);
            }
        }

        return salaryLogBO.saveSalaryLog(salaryCode, salary.getStaffCode(),
            ESalaryLogType.Abnormal.getCode(), handler, handleNote,
            stringBuffer.toString());
    }

    @Override
    @Transactional
    public String changeToNormal(String salaryCode, String handler,
            String handleNote) {

        Salary salary = salaryBO.getSalary(salaryCode);
        if (ESalaryStatus.Pay_Again.getCode().equals(salary.getStatus())) {
            throw new BizException("xn00000", "该工资条已转正常，请勿重复操作");
        }

        // 添加用户事件
        String code = salaryLogBO.saveSalaryLog(salary.getCode(),
            salary.getStaffCode(), ESalaryLogType.Normal.getCode(), handler,
            handleNote, null);

        // 添加系统事件
        salaryLogBO.saveSalaryLog(salary.getCode(), salary.getStaffCode(),
            ESalaryLogType.Normal.getCode(), "admin", "监管单位确认事件已处理完成，转为正常状态。",
            null);

        // 改变工资条状态
        salaryBO.refreshStatus(salaryCode, ESalaryStatus.Pay_Again.getCode());

        return code;
    }

    @Override
    public Paginable<SalaryLog> querySalaryLogPage(int start, int limit,
            SalaryLog condition) {
        Paginable<SalaryLog> page = salaryLogBO.getPaginable(start, limit,
            condition);
        for (SalaryLog salaryLog : page.getList()) {
            initSalaryLog(salaryLog);
        }
        return page;
    }

    @Override
    public List<SalaryLog> querySalaryLogList(SalaryLog condition) {
        List<SalaryLog> list = salaryLogBO.querySalaryLogList(condition);
        for (SalaryLog salaryLog : list) {
            initSalaryLog(salaryLog);
        }
        return list;
    }

    @Override
    public SalaryLog getSalaryLog(String code) {
        SalaryLog data = salaryLogBO.getSalaryLog(code);

        Salary salary = salaryBO.getSalary(data.getSalaryCode());

        data.setSalary(salary);
        initSalaryLog(data);

        return data;
    }

    private void initSalaryLog(SalaryLog salaryLog) {
        Staff staff = staffBO.getStaffBrief(salaryLog.getStaffCode());
        salaryLog.setStaffName(staff.getName());

        List<String> handlePicList = new ArrayList<String>();
        if (StringUtils.isNotBlank(salaryLog.getHandlePic())) {
            String[] handlePicArray = salaryLog.getHandlePic().split(",");
            for (String handlePic : handlePicArray) {
                if (StringUtils.isNotBlank(handlePic)) {
                    handlePicList.add(handlePic);
                }
            }
        }
        salaryLog.setHandlePicList(handlePicList);
    }
}
