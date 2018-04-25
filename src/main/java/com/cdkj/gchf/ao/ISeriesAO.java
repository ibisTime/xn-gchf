package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Series;
import com.cdkj.gchf.dto.req.XN630410Req;
import com.cdkj.gchf.dto.req.XN630412Req;

public interface ISeriesAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 新增品牌
    public String addSeries(XN630410Req req);

    // 修改品牌
    public void editSeries(XN630412Req req);

    // 上架品牌
    public void upSeries(String code, String location, String orderNo,
            String updater, String remark);

    // 下架品牌
    public void downSeries(String code, String updater, String remark);

    // 分页查询
    public Paginable<Series> querySeriesPage(int start, int limit,
            Series condition);

    // 详情查询
    public Series getSeries(String code);

    // 列表查询
    public List<Series> querySeriesList(Series condition);
}
