package com.cdkj.gchf.dto.req;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 上传项目人员
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631911Req {

    // 项目编码
    @NotBlank
    private String projectCode;

    // 班组所在企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 班组所在企业名称
    @NotBlank
    private String corpName;

    // 班组编号
    @Min(0)
    private Integer teamSysNo;

    // 班组名称
    @NotBlank
    private String teamName;

    // 工人列表
    @NotEmpty
    private List<XN631911ReqWorker> workerList;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<XN631911ReqWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<XN631911ReqWorker> workerList) {
        this.workerList = workerList;
    }

}
