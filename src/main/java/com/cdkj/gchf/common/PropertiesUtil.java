package com.cdkj.gchf.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties props;

    static {
        props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到config.properties文件", e);
        } catch (IOException e) {
            throw new RuntimeException("读取config.properties文件出错", e);
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            // throw new Exception("配置文件初始化失败");
        }
        return props.getProperty(key);
    }

    public static final class Config {

        public static String SMS_URL = props.getProperty("SMS_URL");

        public static String FEAT_URL = props.getProperty("FEAT_URL");

        public static String GOV_URL = props.getProperty("GOV_URL");

        public static String HLS_STREAM_DIR = props.getProperty("HLS_STREAM_DIR");

        public static String OCR_HOST = props.getProperty("OCR_HOST");

        public static String OCR_PATH = props.getProperty("OCR_PATH");

        public static String OCR_APP_KEY = props.getProperty("OCR_APP_KEY");

        public static String ZQZN_APP_KEY = props.getProperty("ZQZN_APP_KEY");

        public static String HLS_ENV = props.getProperty("HLS_ENV");

        public static String HLS_HOST = props.getProperty("HLS_HOST");

        public static String OCR_APP_SECRET = props
            .getProperty("OCR_APP_SECRET");

        public static String OCR_APP_CODE = props.getProperty("OCR_APP_CODE");

        public static String IMAGE_FILE_DIR = props
            .getProperty("IMAGE_FILE_DIR");

        public static String KEY_STORE_PATH = props
            .getProperty("KEY_STORE_PATH");

        public static String ETH_NODE_INFURA = props
            .getProperty("ETH_NODE_INFURA");

        public static String ETH_NODE_BCOIN = props
            .getProperty("ETH_NODE_BCOIN");

    }
}
