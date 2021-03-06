package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * @ClassName: EWorkerAttendanceUploadStatus
 * @Description:人员考勤上传状态
 * @author: Old3
 * @date: 2019年4月30日 上午10:40:22
 * @Copyright:
 */
public enum EWorkerAttendanceUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    UPLOAD_UNEDITABLE("3", "已上传不可修改"),

    UPLOADING("4", "上传中");

    EWorkerAttendanceUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;

    }

    public static Map<String, EWorkerAttendanceUploadStatus> getUploadStatusMap() {
        Map<String, EWorkerAttendanceUploadStatus> map = new HashMap<String, EWorkerAttendanceUploadStatus>();
        for (EWorkerAttendanceUploadStatus type : EWorkerAttendanceUploadStatus
                .values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EWorkerAttendanceUploadStatus getUploadStatus(String code) {
        Map<String, EWorkerAttendanceUploadStatus> map = getUploadStatusMap();
        EWorkerAttendanceUploadStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EWorkerAttendanceUploadStatus> map = getUploadStatusMap();
        EWorkerAttendanceUploadStatus projectCorpType = map.get(code);
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
