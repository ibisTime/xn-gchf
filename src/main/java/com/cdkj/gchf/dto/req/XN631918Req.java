package com.cdkj.gchf.dto.req;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 上传人员考勤
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631918Req {

    // 项目编码
    @NotBlank
    private String projectCode;

    // 班组编号
    @Min(0)
    private Integer teamSysNo;

    @NotEmpty
    private List<XN631918ReqData> dataList;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public List<XN631918ReqData> getDataList() {
        return dataList;
    }

    public void setDataList(List<XN631918ReqData> dataList) {
        this.dataList = dataList;
    }

}
