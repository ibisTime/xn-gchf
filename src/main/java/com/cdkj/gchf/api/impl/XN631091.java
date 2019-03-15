package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.QnTokenImpl;
import com.cdkj.gchf.dto.res.XN631091Res;
import com.cdkj.gchf.enums.ESystemCode;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 上传图片凭证
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN631091 extends AProcessor {

    private QnTokenImpl qnTokenImpl = SpringContextHolder
        .getBean(QnTokenImpl.class);

    @Override
    public Object doBusiness() throws BizException {
        return new XN631091Res(qnTokenImpl.getUploadToken(
            ESystemCode.GCHF.getCode(), ESystemCode.GCHF.getCode()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
    }

}
