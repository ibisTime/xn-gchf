package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * @ClassName: ETeamMasterUploadStatus
 * @Description:项目班组上传状态
 * @author: Old3
 * @date: 2019年4月29日 下午9:38:04
 * @Copyright:
 */
public enum ETeamMasterUploadStatus {
    UPLOAD_FAIL("-1", "上传失败"),

    TO_UPLOAD("0", "待上传"),

    UPLOAD_UPDATE("1", "已上传已同步"),

    UPLOAD_UNUPDATE("2", "已上传未同步"),

    UPLOADING("4", "上传中"),

    UPDATEING("5", "同步中");

    ETeamMasterUploadStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ETeamMasterUploadStatus> getUploadStatusMap() {
        Map<String, ETeamMasterUploadStatus> map = new HashMap<String, ETeamMasterUploadStatus>();
        for (ETeamMasterUploadStatus type : ETeamMasterUploadStatus.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ETeamMasterUploadStatus getUploadStatus(String code) {
        Map<String, ETeamMasterUploadStatus> map = getUploadStatusMap();
        ETeamMasterUploadStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应上传状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, ETeamMasterUploadStatus> map = getUploadStatusMap();
        ETeamMasterUploadStatus projectCorpType = map.get(code);
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
