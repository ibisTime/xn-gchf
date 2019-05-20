package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.common.StringUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631795Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.enums.EOcrSide;
import com.cdkj.gchf.enums.EOcrType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.humanfaces.AppConfig;
import com.cdkj.gchf.ocr.*;
import com.cdkj.gchf.spring.SpringContextHolder;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author old3
 * @title: XN631795
 * @description: ocr识别
 * @date 2019-05-16 14:15
 */
public class XN631795 extends AProcessor {
    private XN631795Req req = null;

    private IWorkerInfoAO workerInfoAO = SpringContextHolder.getBean(IWorkerInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        Gson gson = new Gson();
        String positive = OcrConnecter.getOrcData(req.getPositiveImage().substring(req.getPositiveImage().indexOf(",") + 1), EOcrSide.FACE.getCode());
        String negative = OcrConnecter.getOrcData(req.getNegativeImage().substring(req.getNegativeImage().indexOf(",") + 1), EOcrSide.BACK.getCode());
        //录入
        if (EOcrType.INPUT.getCode().equals(req.getType())) {
            return new PKCodeRes(workerInfoAO.addWorkerInfo(req));
        }
        //一张识别失败
        if (positive != null && negative == null) {
            return new OcrResponse(gson.fromJson(positive, PositiveRes.class), null);
        }

        if (negative != null && positive == null) {
            return new OcrResponse(null, gson.fromJson(negative, NegativeRes.class));
        }
        if (positive != null && negative != null) {
            return new OcrResponse(gson.fromJson(positive, PositiveRes.class), gson.fromJson(negative, NegativeRes.class));
        } else {
            throw new BizException("XN000000", "识别失败，请重新上传识别");
        }
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631795Req.class);
        ObjValidater.validateReq(req);
    }
}
