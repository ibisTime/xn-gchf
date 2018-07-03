package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 手动上班打卡
 * @author: silver 
 * @since: 2018年6月25日 下午1:54:33 
 * @history:
 */
public class XN631390Req {
    // 考勤编号
    @NotEmpty
    private List<String> codeList;

    // 上班考勤时间
    @NotBlank
    private String startDatetime;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

}
