package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CarOrder;
import com.cdkj.gchf.dto.req.XN630430Req;

public interface ICarOrderAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 申请
    public String addCarOrder(XN630430Req req);

    // 处理
    public void editCarOrder(String code, String result, String handler,
            String remark);

    // 分页查询
    public Paginable<CarOrder> queryCarPage(int start, int limit,
            CarOrder condition);

    // 详情查询
    public CarOrder getCarOrder(String code);

    // 列表查询
    public List<CarOrder> queryCarOrderList(CarOrder condition);

}
