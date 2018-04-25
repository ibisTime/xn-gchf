package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IGoogleAuthAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.dto.res.XN805070Res;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 获取谷歌验证秘钥
 * @author: haiqingzheng 
 * @since: 2017年12月6日 下午4:10:53 
 * @history:
 */
public class XN805070 extends AProcessor {

    private IGoogleAuthAO googleAuthAO = SpringContextHolder
        .getBean(IGoogleAuthAO.class);

    @Override
    public Object doBusiness() throws BizException {
        String secret = googleAuthAO.generateSecretKey();
        return new XN805070Res(secret);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
    }

}
