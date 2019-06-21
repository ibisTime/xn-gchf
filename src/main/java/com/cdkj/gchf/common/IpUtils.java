package com.cdkj.gchf.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : old3
 * @since : 2019-06-21 15:08
 */
public class IpUtils {

    public static boolean checkIpValidation(String ip) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =
                pattern.matcher(ip);
        return matcher.find();
    }
}

    
    