package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 政治面貌类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EPoliticsType {

    ZHONGGONGDANGYUAN("01", "中共党员"),

    YUBEIDANGYUAN("02", "中共预备党员"),

    GONGQINGTUANYUAN("03", "共青团员"),

    MINGEDANGYUAN("04", "民革党员"),

    MINMENGMENGYUAN("05", "民盟盟员"),

    MINJIANHUIYUAN("06", "民建会员"),

    MINJINHUIYUAN("07", "民进会员"),

    MINGONGDANGDANGYUAN("08", "农工党党员"),

    ZHIGONGDANGDANGYUAN("09", "致公党党员"),

    JIUSANXUESHESHEYUAN("10", "九三学社社员"),

    TAIMENGMENGYUAN("11", "台盟盟员"),

    WUDANGPAIRENYUAN("12", "无党派人士"),

    QUNZHONG("13", "群众");

    public static Map<String, EPoliticsType> getPoliticsTypeMap() {
        Map<String, EPoliticsType> map = new HashMap<String, EPoliticsType>();
        for (EPoliticsType type : EPoliticsType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EPoliticsType getPoliticsType(String code) {
        Map<String, EPoliticsType> map = getPoliticsTypeMap();
        EPoliticsType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应政治面貌类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EPoliticsType> map = getPoliticsTypeMap();
        EPoliticsType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应政治面貌类型不存在");
        }
    }

    EPoliticsType(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
