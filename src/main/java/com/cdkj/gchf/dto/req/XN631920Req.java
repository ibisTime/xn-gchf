package com.cdkj.gchf.dto.req;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cdkj.gchf.gov.APIRequestBase;

/**
 * 上传人员工资
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631920Req extends APIRequestBase {

    private static final long serialVersionUID = -4838021997630817623L;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 所属企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 所属企业名称
    @NotBlank
    private String corpName;

    // 班组编号
    @Min(0)
    private Integer teamSysNo;

    // 发放工资的年月
    @NotBlank
    private String payMonth;

    @NotEmpty
    private List<XN631920ReqDateil> detailList;

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

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public List<XN631920ReqDateil> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<XN631920ReqDateil> detailList) {
        this.detailList = detailList;
    }

}
