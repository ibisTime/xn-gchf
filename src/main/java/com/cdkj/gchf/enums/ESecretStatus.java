/**
 * @Title ECheckResult.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午2:58:54 
 * @version V1.0   
 */
package com.cdkj.gchf.enums;

/**
 * 项目秘钥状态
 * @author: silver 
 * @since: Apr 11, 2019 9:51:54 PM 
 * @history:
 */
public enum ESecretStatus {
    YES("1", "已配置"),

    NO("0", "未配置");

    ESecretStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
