package com.cdkj.gchf.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISmsOutAO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.enums.ECaptchaType;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void sendSmsCaptcha(String mobile, String bizType) {
        if (ECaptchaType.C_REG.getCode().equals(bizType)) {
            // userBO.isMobileExist(mobile, EUserKind.Customer.getCode(),
            // companyCode, systemCode);
        }
        smsOutBO.sendSmsCaptcha(mobile, bizType);
    }

    @Override
    public void sendContent(String tokenId, String mobile, String content) {
        PhoneUtil.checkMobile(mobile);
        // smsOutBO.sendSmsOut(mobile, content, null, null, null);
    }
}
