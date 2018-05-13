package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ICcontractDAO;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.exception.BizException;

@Component
public class CcontractBOImpl extends PaginableBOImpl<Ccontract>
        implements ICcontractBO {

    @Autowired
    private ICcontractDAO ccontractDAO;

    public void saveCcontract(Ccontract data) {
        ccontractDAO.insert(data);
    }

    @Override
    public void removeCcontract(String code) {
    }

    @Override
    public void refreshCcontract(Ccontract data) {
        ccontractDAO.update(data);
    }

    @Override
    public List<Ccontract> queryCcontractList(Ccontract condition) {
        return ccontractDAO.selectList(condition);
    }

    @Override
    public Ccontract getCcontract(String code) {
        Ccontract data = null;
        if (StringUtils.isNotBlank(code)) {
            Ccontract condition = new Ccontract();
            condition.setCode(code);
            data = ccontractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "合同信息不存在");
            }
        }
        return data;
    }

    @Override
    public void isExist(String projectCode, String staffCode) {
        Ccontract condition = new Ccontract();
        condition.setProjectCode(projectCode);
        condition.setStaffCode(staffCode);
        Ccontract data = ccontractDAO.select(condition);
        if (data != null) {
            throw new BizException("xn0000", "合同信息不存在");
        }
    }
}
