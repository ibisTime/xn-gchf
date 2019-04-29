package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 
 * @ClassName:  EProjectCorpUploadStatus   
 * @Description:参建单位上传状态
 * @author: Old3
 * @date:   2019年4月29日 下午8:48:32     
 * @Copyright:
 */
public enum EProjectCorpUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    UPLOAD_UPDATE("1", "已上传已同步"),

    UPLOAD_UNUPDATE("2", "已上传未同步"),

    UPLOADING("4", "上传中"),

    UPDATEING("5", "同步中");

    EProjectCorpUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EProjectCorpUploadStatus> getUploadStatusMap() {
        Map<String, EProjectCorpUploadStatus> map = new HashMap<String, EProjectCorpUploadStatus>();
        for (EProjectCorpUploadStatus type : EProjectCorpUploadStatus
            .values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EProjectCorpUploadStatus getUploadStatus(String code) {
        Map<String, EProjectCorpUploadStatus> map = getUploadStatusMap();
        EProjectCorpUploadStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EProjectCorpUploadStatus> map = getUploadStatusMap();
        EProjectCorpUploadStatus projectCorpType = map.get(code);
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
