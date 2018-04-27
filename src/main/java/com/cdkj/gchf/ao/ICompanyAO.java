package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.dto.req.XN631022Req;
import com.cdkj.gchf.dto.req.XN631420Req;

public interface ICompanyAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增公司
    public String addCompany(XN631420Req req);

    // 删除公司
    public void deleteCompany(String code);

    // 修改公司
    public void editCompany(XN631022Req req);

    // 分页查询
    Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    // 详情查询
    public Company getCompany(String code);

    // 列表查询
    public List<Company> queryCompanyList(Company condition);

}
