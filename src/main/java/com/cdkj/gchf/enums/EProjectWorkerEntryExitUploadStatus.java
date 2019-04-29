package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 
 * @ClassName:  ETeamMasterUploadStatus   
 * @Description:项目班组上传状态
 * @author: Old3
 * @date:   2019年4月29日 下午9:38:04     
 * @Copyright:
 */
public enum EProjectWorkerEntryExitUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    UPLOAD_UNEDITABLE("3", "已上传不可修改"),

    UPLOADING("4", "上传中");

    EProjectWorkerEntryExitUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;

    }

    public static Map<String, EProjectWorkerEntryExitUploadStatus> getUploadStatusMap() {
        Map<String, EProjectWorkerEntryExitUploadStatus> map = new HashMap<String, EProjectWorkerEntryExitUploadStatus>();
        for (EProjectWorkerEntryExitUploadStatus type : EProjectWorkerEntryExitUploadStatus
            .values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EProjectWorkerEntryExitUploadStatus getUploadStatus(
            String code) {
        Map<String, EProjectWorkerEntryExitUploadStatus> map = getUploadStatusMap();
        EProjectWorkerEntryExitUploadStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EProjectWorkerEntryExitUploadStatus> map = getUploadStatusMap();
        EProjectWorkerEntryExitUploadStatus projectCorpType = map.get(code);
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
