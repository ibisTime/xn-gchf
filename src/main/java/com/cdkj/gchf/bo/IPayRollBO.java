package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;

public interface IPayRollBO extends IPaginableBO<PayRoll> {

    public String savePayRoll(XN631770Req data);

    public int removePayRoll(String code);

    public int refreshPayRoll(PayRoll data);

    public List<PayRoll> queryPayRollList(PayRoll condition);

    public PayRoll getPayRoll(String code);

    /****国家平台接口****/
    public void doUpload(XN631920Req req, ProjectConfig projectConfig);

    public Paginable<PayRollDetail> doQuery(XN631921Req req,
            ProjectConfig projectConfig);
}
