package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISuperviseBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ISuperviseDAO;
import com.cdkj.gchf.domain.Supervise;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

/**
 * 监管单位
 * @author: silver 
 * @since: 2018年8月1日 上午10:35:51 
 * @history:
 */
@Component
public class SuperviseBOImpl extends PaginableBOImpl<Supervise>
        implements ISuperviseBO {

    @Autowired
    private ISuperviseDAO superviseDAO;

    @Override
    public String saveSupervise(String province, String city, String area) {
        String code = null;
        if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city)
                && StringUtils.isNotBlank(area)) {
            Supervise data = new Supervise();
            code = OrderNoGenerater
                .generate(EGeneratePrefix.Supervise.getCode());
            data.setCode(code);
            data.setProvince(province);
            data.setCity(city);
            data.setArea(area);
            data.setUpdateDatetime(new Date());
            superviseDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<Supervise> querySuperviseList(Supervise condition) {
        return superviseDAO.selectList(condition);
    }

    @Override
    public Supervise getSupervise(String code) {
        Supervise data = null;
        if (StringUtils.isNotBlank(code)) {
            Supervise condition = new Supervise();
            condition.setCode(code);
            data = superviseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "监管单位不存在！");
            }
        }
        return data;
    }

    @Override
    public Supervise getSupervise(String province, String city, String area) {
        Supervise data = null;
        if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city)
                && StringUtils.isNotBlank(area)) {
            Supervise condition = new Supervise();
            condition.setProvince(province);
            condition.setCity(city);
            condition.setArea(area);
            data = superviseDAO.select(condition);
        }
        return data;
    }
}
