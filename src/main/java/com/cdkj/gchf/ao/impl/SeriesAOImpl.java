package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISeriesAO;
import com.cdkj.gchf.bo.ISeriesBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Series;
import com.cdkj.gchf.dto.req.XN630410Req;
import com.cdkj.gchf.dto.req.XN630412Req;
import com.cdkj.gchf.enums.EBrandStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class SeriesAOImpl implements ISeriesAO {

    @Autowired
    private ISeriesBO seriesBO;

    @Override
    public String addSeries(XN630410Req req) {
        Series series = new Series();
        series.setBrandCode(req.getBrandCode());
        series.setName(req.getName());
        series.setSlogan(req.getSlogan());
        series.setAdvPic(req.getAdvPic());
        series.setPrice(StringValidater.toLong(req.getPrice()));
        series.setStatus(EBrandStatus.TO_UP.getCode());
        series.setUpdater(req.getUpdater());
        series.setUpdateDatetime(new Date());
        series.setRemark(req.getRemark());
        return seriesBO.saveSeries(series);
    }

    @Override
    public void editSeries(XN630412Req req) {
        Series series = seriesBO.getSeries(req.getCode());
        if (EBrandStatus.UP.getCode().equals(series.getStatus())) {
            throw new BizException("xn0000", "品牌已上架，请在下架后修改");
        }
        series.setBrandCode(req.getBrandCode());
        series.setName(req.getName());
        series.setSlogan(req.getSlogan());
        series.setAdvPic(req.getAdvPic());
        series.setPrice(StringValidater.toLong(req.getPrice()));
        series.setUpdater(req.getUpdater());
        series.setUpdateDatetime(new Date());
        series.setRemark(req.getRemark());
        seriesBO.editSeries(series);
    }

    @Override
    public void upSeries(String code, String location, String orderNo,
            String updater, String remark) {
        Series series = seriesBO.getSeries(code);
        series.setLocation(StringValidater.toInteger(location));
        series.setOrderNo(StringValidater.toInteger(orderNo));
        series.setStatus(EBrandStatus.UP.getCode());
        series.setUpdater(updater);
        series.setUpdateDatetime(new Date());
        series.setRemark(remark);
        seriesBO.upSeries(series);
    }

    @Override
    public void downSeries(String code, String updater, String remark) {
        Series series = seriesBO.getSeries(code);
        series.setStatus(EBrandStatus.DOWN.getCode());
        series.setUpdater(updater);
        series.setUpdateDatetime(new Date());
        series.setRemark(remark);
        seriesBO.downSeries(series);
    }

    @Override
    public Paginable<Series> querySeriesPage(int start, int limit,
            Series condition) {
        return seriesBO.getPaginable(start, limit, condition);
    }

    @Override
    public Series getSeries(String code) {
        return seriesBO.getSeries(code);
    }

    @Override
    public List<Series> querySeriesList(Series condition) {
        return seriesBO.querySeries(condition);
    }

}
