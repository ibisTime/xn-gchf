package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631812Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;

@Component
public interface IPayRollAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增工资单 
     */
    public String addPayRoll(XN631770Req req);

    /**
     * code删工资单 
     */
    public int dropPayRoll(String code);

    /**
     * 修改工资单
     */
    public int editPayRoll(XN631772Req req);

    /**
     * 导入工资单 
     */
    public void importPayRollCodeList(XN631812Req req);

    /**
     * 上传工资单 
     */
    public void uploadPayRollList(String userId, List<String> codeList);

    public Paginable<PayRoll> queryPayRollPage(int start, int limit,
            PayRoll condition);

    public List<PayRoll> queryPayRollList(PayRoll condition);

    public PayRoll getPayRoll(String code);

    public void refreshPayRollCodeByLocal(String code, String payRollCode);

    /****国家平台接口****/
    public void uploadPayRoll(XN631920Req data);

    public Paginable<PayRollDetail> queryPayRoll(XN631921Req req);
}
