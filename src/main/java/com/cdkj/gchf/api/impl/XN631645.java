package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631645Req;
import com.cdkj.gchf.enums.ECorpType;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631645   
 * @Description: 分页查参建单位
 * @author: Old3
 * @date:   2019年3月19日 下午11:02:03     
 * @Copyright:
 */
public class XN631645 extends AProcessor {
    private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631645Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectCorpInfo condition = new ProjectCorpInfo();
        condition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        BeanUtils.copyProperties(req, condition);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProjectCorpInfoAO.DEFAULT_ORDER_COLUMN;
        }
        if (StringUtils.isNotBlank(req.getUploadStatus())) {
            EUploadStatus.checkExists(req.getUploadStatus());
        }
        if (StringUtils.isNotBlank(req.getCorpType())) {
            ECorpType.checkExists(req.getCorpType());
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return projectCorpInfoAO.queryProjectCorpInfoPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631645Req.class);
        ObjValidater.validateReq(req);
    }

}
