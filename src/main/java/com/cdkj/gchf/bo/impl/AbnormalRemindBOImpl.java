package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IAbnormalRemindBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IAbnormalRemindDAO;
import com.cdkj.gchf.domain.AbnormalRemind;
import com.cdkj.gchf.exception.BizException;

@Component
public class AbnormalRemindBOImpl extends PaginableBOImpl<AbnormalRemind>
        implements IAbnormalRemindBO {

    @Autowired
    IAbnormalRemindDAO abnormalRemindDAO;

    @Override
    public void saveAbnormalRemin(AbnormalRemind data) {
        abnormalRemindDAO.insert(data);
    }

    @Override
    public AbnormalRemind getAbnormalRemind(String code) {
        AbnormalRemind data = null;
        if (StringUtils.isNotBlank(code)) {
            AbnormalRemind condition = new AbnormalRemind();
            condition.setCode(code);
            data = abnormalRemindDAO.select(condition);
            if (data == null) {
                throw new BizException("xn00000", "该事件通知人不存在");
            }

        }
        return data;
    }

    @Override
    public void removeAbnormalRemind(AbnormalRemind data) {
        abnormalRemindDAO.delete(data);
    }

    @Override
    public void refreshAbnormalRemind(AbnormalRemind data) {
        abnormalRemindDAO.update(data);
    }

    @Override
    public List<AbnormalRemind> queryAbnormalRemindList(
            AbnormalRemind condition) {
        return abnormalRemindDAO.selectList(condition);
    }

}
