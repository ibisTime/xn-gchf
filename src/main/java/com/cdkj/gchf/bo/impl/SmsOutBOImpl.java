package com.cdkj.gchf.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.dto.req.XN804080Req;
import com.cdkj.gchf.dto.req.XN804081Req;
import com.cdkj.gchf.dto.req.XN804082Req;
import com.cdkj.gchf.dto.req.XN804083Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.enums.ESystemCode;
import com.cdkj.gchf.http.BizConnecter;
import com.cdkj.gchf.http.JsonUtils;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sendSmsCaptcha(String mobile, String bizType) {
        try {
            XN804081Req req = new XN804081Req();
            req.setMobile(mobile);
            req.setBizType(bizType);
            req.setCompanyCode(ESystemCode.GCHF.getCode());
            req.setSystemCode(ESystemCode.GCHF.getCode());
            BizConnecter.getBizData("804081", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }

    @Override
    public void sendEmailCaptcha(String email, String bizType) {
        try {
            XN804083Req req = new XN804083Req();
            req.setEmail(email);
            req.setBizType(bizType);
            req.setCompanyCode(ESystemCode.GCHF.getCode());
            req.setSystemCode(ESystemCode.GCHF.getCode());
            BizConnecter.getBizData("804083", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用邮件发送验证码异常");
        }
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setCompanyCode(ESystemCode.GCHF.getCode());
        req.setSystemCode(ESystemCode.GCHF.getCode());
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void sendSmsOut(String mobile, String content, String bizType,
            String systemCode, String companyCode) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData(bizType, JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }
}
