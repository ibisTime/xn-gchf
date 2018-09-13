/**
 * @Title EUser.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午3:08:34 
 * @version V1.0   
 */
package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午3:08:34 
 * @history:
 */
public enum EUser {
    // li表示程序
    LI("li", "程序"), ADMIN("admin", "系统管理员");

    EUser(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EUser> getMap() {
        Map<String, EUser> map = new HashMap<String, EUser>();
        for (EUser eUser : EUser.values()) {
            map.put(eUser.getCode(), eUser);
        }
        return map;
    }

    public static String getValue(String code) {
        Map<String, EUser> map = getMap();
        return map.get(code).getValue();
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
