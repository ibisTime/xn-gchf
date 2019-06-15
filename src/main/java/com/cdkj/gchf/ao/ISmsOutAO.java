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

    /**
     * 发送验证码验证用户
     *
     * @param mobile 手机号
     * @param bizType 业务编号
     */
    void sendSmsCaptchaCheckUser(String mobile, String bizType);

}
