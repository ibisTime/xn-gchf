package com.cdkj.gchf.common;

import java.io.IOException;
import java.net.InetAddress;
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

    public static boolean checkIpReachable(String ip) {
        boolean status = false;
        try {
            status = InetAddress.getByName(ip).isReachable(300);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static void main(String args[]) {
        System.out.println(checkIpReachable("115.236.183.71"));
    }
}

    
    