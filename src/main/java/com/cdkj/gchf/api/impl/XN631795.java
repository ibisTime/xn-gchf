package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.common.StringUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631795Req;
import com.cdkj.gchf.enums.EOcrSide;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.humanfaces.AppConfig;
import com.cdkj.gchf.ocr.*;
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

    @Override
    public Object doBusiness() throws BizException {
        Gson gson = new Gson();
        String orcData = OcrConnecter.getOrcData(req.getImage().substring(req.getImage().indexOf(",") + 1), req.getSide());
        if (StringUtils.isNotBlank(orcData)) {
            if (req.getSide().equals(EOcrSide.FACE.getCode())) {
                return gson.fromJson(orcData, PositiveRes.class);
            } else {
                return gson.fromJson(orcData, NegativeRes.class);
            }

        } else {
            throw new BizException("XN00000", "识别异常，请尝试修改照片或检查身份证照片方向");
        }
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631795Req.class);
        ObjValidater.validateReq(req);
    }
}
