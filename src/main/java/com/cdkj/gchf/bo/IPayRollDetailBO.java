package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;

public interface IPayRollDetailBO extends IPaginableBO<PayRollDetail> {

    public void savePayRollDetail(String projectCode,
            List<XN631770ReqDetail> data);

    public int deletePayRollDetail(String code);

    public int updatePayRollDetail(PayRollDetail data);

}
