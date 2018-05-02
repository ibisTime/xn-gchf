package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Employ;

public interface IEmployBO extends IPaginableBO<Employ> {

    public void saveEmploy(Employ data);

    public int removeEmploy(String code);

    public int refreshEmploy(Employ data);

    public List<Employ> queryEmployList(Employ condition);

    public Employ getEmploy(String code);

    public void toHoliday(Employ data);

    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark);

}
