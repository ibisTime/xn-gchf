package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProgressBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProgressDAO;
import com.cdkj.gchf.domain.Progress;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProgressBOImpl extends PaginableBOImpl<Progress>
        implements IProgressBO {

    @Autowired
    private IProgressDAO progressDAO;

    public String saveProgress(Progress data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.Progress.getCode());
            data.setCode(code);
            progressDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeProgress(String code) {
        return 0;
    }

    @Override
    public int refreshProgress(Progress data) {
        return progressDAO.updateProgress(data);
    }

    @Override
    public List<Progress> queryProgressList(Progress condition) {
        return progressDAO.selectList(condition);
    }

    @Override
    public Progress getProgress(String code) {
        Progress data = null;
        if (StringUtils.isNotBlank(code)) {
            Progress condition = new Progress();
            condition.setCode(code);
            data = progressDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工程不存在！！！");
            }
        }
        return data;
    }
}
