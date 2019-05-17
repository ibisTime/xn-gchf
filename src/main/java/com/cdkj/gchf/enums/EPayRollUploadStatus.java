package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * @ClassName: EPayRollUploadStatus
 * @Description:工资单上传状态
 * @author: Old3
 * @date: 2019年4月30日 上午10:40:39
 * @Copyright:
 */
public enum EPayRollUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    UPLOAD_UNEDITABLE("3", "已上传不可修改"),

    UPLOADING("4", "上传中");

    EPayRollUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;

    }

    public static Map<String, EPayRollUploadStatus> getUploadStatusMap() {
        Map<String, EPayRollUploadStatus> map = new HashMap<String, EPayRollUploadStatus>();
        for (EPayRollUploadStatus type : EPayRollUploadStatus.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EPayRollUploadStatus getUploadStatus(String code) {
        Map<String, EPayRollUploadStatus> map = getUploadStatusMap();
        EPayRollUploadStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EPayRollUploadStatus> map = getUploadStatusMap();
        EPayRollUploadStatus projectCorpType = map.get(code);
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
