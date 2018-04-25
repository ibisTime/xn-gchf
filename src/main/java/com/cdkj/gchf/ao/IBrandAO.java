package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Brand;
import com.cdkj.gchf.dto.req.XN630400Req;
import com.cdkj.gchf.dto.req.XN630402Req;

public interface IBrandAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增品牌
    public String addBrand(XN630400Req req);

    // 修改品牌
    public void editBrand(XN630402Req req);

    // 上架品牌
    public void upBrand(String code, String updater, String remark);

    // 下架品牌
    public void downBrand(String code, String updater, String remark);

    // 分页查询
    public Paginable<Brand> queryBrandPage(int start, int limit,
            Brand condition);

    // 详情查询
    public Brand getBrand(String code);

    // 列表查询
    public List<Brand> queryBrandList(Brand condition);

}
