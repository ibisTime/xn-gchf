package com.cdkj.gchf.common;

import org.apache.commons.lang3.StringUtils;

public class QiniuUtil {

    public static String parseUrl(String token) {
        String qiniuUrl = null;

        if (StringUtils.isBlank(token)) {
            return null;
        }

        if (token.startsWith("data:image")) {
            return token;
        } else {
            qiniuUrl = "http://picture.jm60s.com/" + token
                    + "?imageMogr2/auto-orient";
        }

        return qiniuUrl;
    }
}
