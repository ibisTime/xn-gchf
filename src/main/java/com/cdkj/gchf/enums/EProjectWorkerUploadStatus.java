package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 
 * @ClassName:  EProjectWorkerUploadStatus   
 * @Description: 项目人员上传状态
 * @author: Old3
 * @date:   2019年4月30日 上午10:39:32     
 * @Copyright:
 */
public enum EProjectWorkerUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    UPLOAD_UPDATE("1", "已上传已同步"),

    UPLOAD_UNUPDATE("2", "已上传未同步"),

    UPLOADING("4", "上传中"),

    UPDATEING("5", "同步中");

    EProjectWorkerUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EProjectWorkerUploadStatus> getUploadStatusMap() {
        Map<String, EProjectWorkerUploadStatus> map = new HashMap<String, EProjectWorkerUploadStatus>();
        for (EProjectWorkerUploadStatus type : EProjectWorkerUploadStatus
            .values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EProjectWorkerUploadStatus getUploadStatus(String code) {
        Map<String, EProjectWorkerUploadStatus> map = getUploadStatusMap();
        EProjectWorkerUploadStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EProjectWorkerUploadStatus> map = getUploadStatusMap();
        EProjectWorkerUploadStatus projectCorpType = map.get(code);
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
