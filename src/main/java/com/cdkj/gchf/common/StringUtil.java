/**
 * @Title StringUtil.java 
 * @Package com.ibis.account.common 
 * @Description 
 * @author miyb  
 * @date 2015-4-7 下午2:12:14 
 * @version V1.0   
 */
package com.cdkj.gchf.common;

/** 
 * @author: miyb 
 * @since: 2015-4-7 下午2:12:14 
 * @history:
 */
public class StringUtil {
    public static String compressString(String... args) {
        StringBuffer str = new StringBuffer();
        for (String arg : args) {
            str.append(arg);
        }
        return str.toString();
    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public static void main(String args[]) {
        System.out.println(isNumber("10已"));
    }
}
