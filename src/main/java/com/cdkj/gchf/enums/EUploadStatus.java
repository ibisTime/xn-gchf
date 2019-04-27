package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 上传状态
 * @author: silver 
 * @since: Mar 21, 2019 2:01:14 PM 
 * @history:
 */
public enum EUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    // UPLOAD_EDITABLE("1", "已上传可修改"),

    // UPLOAD_UNEDITABLE("2", "已上传不可修改");

    UPLOAD_UPDATE("1", "已上传已同步"),

    UPLOAD_UNUPDATE("2", "已上传未同步"),

    UPLOAD_UNEDITABLE("3", "已上传不可修改"),

    UPLOADING("4", "上传中"),

    UPDATEING("5", "同步中");

    EUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EContractPeriodType> getUploadStatusMap() {
        Map<String, EContractPeriodType> map = new HashMap<String, EContractPeriodType>();
        for (EContractPeriodType type : EContractPeriodType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EContractPeriodType getUploadStatus(String code) {
        Map<String, EContractPeriodType> map = getUploadStatusMap();
        EContractPeriodType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EContractPeriodType> map = getUploadStatusMap();
        EContractPeriodType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
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
