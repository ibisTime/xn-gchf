package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICorpBasicinfoDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class CorpBasicinfoBOImpl extends PaginableBOImpl<CorpBasicinfo>
        implements ICorpBasicinfoBO {

    @Autowired
    private ICorpBasicinfoDAO corpBasicinfoDAO;

    @Override
    public String saveCorpBasicinfo(XN631250Req req) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CorpBasicinfo.getCode());

        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();
        BeanUtils.copyProperties(req, corpBasicinfo);
        corpBasicinfo.setCode(code);

        corpBasicinfoDAO.insert(corpBasicinfo);

        return code;
    }

    @Override
    public int removeCorpBasicinfo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CorpBasicinfo data = new CorpBasicinfo();
            data.setCode(code);
            count = corpBasicinfoDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshCorpBasicinfo(XN631251Req data) {

        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();
        BeanUtils.copyProperties(data, corpBasicinfo);

        corpBasicinfoDAO.update(corpBasicinfo);
    }

    @Override
    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition) {
        return corpBasicinfoDAO.selectList(condition);
    }

    @Override
    public CorpBasicinfo getCorpBasicinfo(String code) {
        CorpBasicinfo data = null;
        if (StringUtils.isNotBlank(code)) {
            CorpBasicinfo condition = new CorpBasicinfo();
            condition.setCode(code);
            data = corpBasicinfoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "企业基本信息编号不存在");
            }
        }
        return data;
    }

    @Override
    public CorpBasicinfo getCorpBasicinfoByCorp(String corpCode) {
        CorpBasicinfo condition = new CorpBasicinfo();
        condition.setCorpCode(corpCode);
        return corpBasicinfoDAO.select(condition);
    }
}
