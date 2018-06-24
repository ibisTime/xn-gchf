package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631390Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 手动考勤打卡：
 * 
 * 
 * @TODO 分为2个接口上班打卡，下班打卡。入参为考勤code和具体时间。
 * @author: nyc 
 * @since: 2018年4月30日 下午9:11:05 
 * @history:
 */
public class XN631390 extends AProcessor {

    private IAttendanceAO attendanceAO = SpringContextHolder
        .getBean(IAttendanceAO.class);

    private XN631390Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        attendanceAO.clockIn(req.getProjectCode(), req.getStaffCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631390Req.class);
        ObjValidater.validateReq(req);
    }

}
