package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;

public interface IPayRollBO extends IPaginableBO<PayRoll> {
    /**
     * 保存工资单 
     */
    public String savePayRoll(XN631770Req data, CorpBasicinfo corpBasicInfo);

    public String savePayRoll(PayRoll payRoll);

    /**
     *保存工资单 
     */
    String savePayRoll(String corpCode, String projectCode, String corpName,
            String teamMasterNo, String payMonth);

    public int removePayRoll(String code);

    public int refreshPayRoll(PayRoll data);

    public int updatePayRollDetail(XN631772Req req);

    int updatePayRollDeleteStatus(String projectCode, String teamMasterNo,
            String corpCode);

    int updatePayRollDeleteStatus(String payRollCode);

    void refreshPayRollCodeByLocal(String code, String payRollCode);

    public List<PayRoll> queryPayRollList(PayRoll condition);

    List<PayRoll> queryPayRollList(String projectCode, String teamMasterNo,
            String corpCode);

    public PayRoll getPayRoll(String code);

    List<PayRoll> getPayRollList(String payRollCode);

    public PayRoll getPayRollByCondition(PayRoll condition);

    PayRoll getPayRollByCorpCodeAndTeamSysNoAndProjectCode(String corpCode,
            String teamMasterSysNo, String projectCode, String payMonth);

    /****国家平台接口****/
    public void doUpload(XN631920Req req, ProjectConfig projectConfig);

    public Paginable<PayRollDetail> doQuery(XN631921Req req,
            ProjectConfig projectConfig);
}
