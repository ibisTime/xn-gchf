package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.dto.req.XN631395Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询考勤记录
 * @author: nyc 
 * @since: 2018年4月30日 下午9:11:05 
 * @history:
 */
public class XN631396 extends AProcessor {

    private IAttendanceAO attendanceAO = SpringContextHolder
        .getBean(IAttendanceAO.class);

    private XN631395Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Attendance condition = new Attendance();
        condition.setProjectCode(req.getProjectCode());
        condition.setStatus(req.getStatus());
        condition.setCreateDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateDatetimeEnd(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return attendanceAO.queryAttendanceList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631395Req.class);
        ObjValidater.validateReq(req);
    }

}
