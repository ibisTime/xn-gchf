package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Employ;

//dao层 
public interface IEmployDAO extends IBaseDAO<Employ> {
    String NAMESPACE = IEmployDAO.class.getName().concat(".");

    int update(Employ data);

    void toHoliday(Employ data);

    void leaveOffice(Employ data);

    long getSalaryCount(Employ condition);

    void updateStatus(Employ data);

    // 修改工资发放状态
    void updateSalaryStatus(Employ data);

    // 更新离职状态
    public void updateLeavingStatus(Employ employ);
}
