package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631647Req;
import com.cdkj.gchf.enums.ECorpType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查参建单位
 * @ClassName:  XN631647   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月19日 下午10:42:49     
 * @Copyright:
 */
public class XN631647 extends AProcessor {
    private IProjectCorpInfoAO corpProjectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631647Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectCorpInfo condition = new ProjectCorpInfo();
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
        condition.setOrder(column, condition.getOrder());
        return corpProjectCorpInfoAO.queryProjectCorpInfoList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631647Req.class);
        ObjValidater.validateReq(req);
    }

}
