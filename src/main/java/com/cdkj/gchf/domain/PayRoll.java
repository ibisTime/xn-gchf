package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;
import com.cdkj.gchf.dto.req.XN631920ReqDateil;

/**
* 人员工资单
* @author: xiongk 
* @since: 2019-03-16 11:15:45
* @history:
*/
public class PayRoll extends ABaseDO {

    private static final long serialVersionUID = -3098618742562890001L;

    // 编号
    private String code;

    // 工资单编码
    private String payRollCode;

    // 项目编码
    private String projectCode;

    // 所属企业统一社会信用代码
    private String corpCode;

    // 所属企业名称
    private String corpName;

    // 班组编号
    private String teamSysNo;

    // 查询条件
    private Date payMonth;

    // 工资年月
    private Date realPayMonth;

    /****DB Properties****/
    // 明细列表
    private List<XN631920ReqDateil> detailList;

    // 明细列表
    private List<PayRollDetail> payRollDetailList;

    // 发放工资的年月
    private String payMonthString;

    // 删除状态
    private String deleteStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayRollCode() {
        return payRollCode;
    }

    public void setPayRollCode(String payRollCode) {
        this.payRollCode = payRollCode;
    }

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

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public Date getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(Date payMonth) {
        this.payMonth = payMonth;
    }

    public List<XN631920ReqDateil> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<XN631920ReqDateil> detailList) {
        this.detailList = detailList;
    }

    public List<PayRollDetail> getPayRollDetailList() {
        return payRollDetailList;
    }

    public void setPayRollDetailList(List<PayRollDetail> payRollDetailList) {
        this.payRollDetailList = payRollDetailList;
    }

    public String getPayMonthString() {
        return payMonthString;
    }

    public void setPayMonthString(String payMonthString) {
        this.payMonthString = payMonthString;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getRealPayMonth() {
        return realPayMonth;
    }

    public void setRealPayMonth(Date realPayMonth) {
        this.realPayMonth = realPayMonth;
    }

}
