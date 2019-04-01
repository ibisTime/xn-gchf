package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631813Req;
import com.google.gson.JsonObject;

public interface IPayRollDetailBO extends IPaginableBO<PayRollDetail> {

    public void savePayRollDetail(String projectCode,
            List<XN631770ReqDetail> data);

    public String savePayRollDetail(PayRollDetail payRollDetail);

    public int deletePayRollDetailByPayRollCode(String payRollCode);

    public int deletePayRollDetail(String code);

    public int updatePayRollDetail(XN631772Req req);

    public int updatePayRollDetail(XN631813Req data);

    public List<PayRollDetail> queryList(PayRollDetail condition);

    public List<PayRollDetail> queryListByPayRollDetail(String payRollCode);

    public PayRollDetail getPayRollDetail(String code);

    public List<PayRollDetail> getPayRollDetailByPayRollCode(
            String payRollCode);

    public JsonObject getUploadRequestJsontoPlantform(PayRoll payRollData,
            TeamMaster teamMasterData, ProjectConfig projectConfigData,
            PayRollDetail payRollDetailData);

}
