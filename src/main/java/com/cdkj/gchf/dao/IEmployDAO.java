package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Employ;

//dao层 
public interface IEmployDAO extends IBaseDAO<Employ> {
    String NAMESPACE = IEmployDAO.class.getName().concat(".");

    int update(Employ data);

    // 请假
    void updateHoliday(Employ data);

    // 离职
    void updateLeaveOffice(Employ data);

    // 获取工资条数量
    long getSalaryCount(Employ condition);

    // 更新状态
    void updateStatus(Employ data);

    // 修改工资发放状态
    void updateSalaryStatus(Employ data);

}
