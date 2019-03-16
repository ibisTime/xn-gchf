package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 上传人员进退场
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631914Req {

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
    @NotBlank
    private Integer teamSysNo;

    @NotEmpty
    private List<XN631914ReqWorker> workerList;

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

    public List<XN631914ReqWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<XN631914ReqWorker> workerList) {
        this.workerList = workerList;
    }

}
