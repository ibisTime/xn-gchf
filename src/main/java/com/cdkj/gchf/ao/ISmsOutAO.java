package com.cdkj.gchf.ao;

public interface ISmsOutAO {
    /**
     * 发送短信验证码
     * @param mobile
     * @param bizType
     * @param companyCode
     * @param systemCode 
     * @create: 2016年12月15日 上午7:28:43 xieyj
     * @history:
     */
    void sendSmsCaptcha(String mobile, String bizType);

    /**
     * 发送内容
     * @param tokenId
     * @param userId
     * @param content 
     * @create: 2016年11月10日 上午10:30:53 xieyj
     * @history:
     */
    void sendContent(String tokenId, String userId, String content);

}
