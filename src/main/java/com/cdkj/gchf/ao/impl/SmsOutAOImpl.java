package com.cdkj.gchf.ao.impl;

import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISmsOutAO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.common.PhoneUtil;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void sendSmsCaptcha(String mobile, String bizType) {
        smsOutBO.sendSmsCaptcha(mobile, bizType);
    }

    @Override
    public void sendContent(String tokenId, String mobile, String content) {
        PhoneUtil.checkMobile(mobile);
        smsOutBO.sendSmsOut(mobile, content, null);
    }

    @Override
    public void sendSmsCaptchaCheckUser(String mobile, String bizType) {
        boolean b = userBO.checkMobile(mobile);
        if (!b) {
            throw new BizException("XN000000", "用户不存在");
        }
    }
}
