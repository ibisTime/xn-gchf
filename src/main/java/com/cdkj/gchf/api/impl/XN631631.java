package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IChannelBankAO;
import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631631Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;
/**
 * 
 * @ClassName:  XN631631   
 * @Description:删除参建单位
 * @author: Old3
 * @date:   2019年3月19日 下午10:47:02     
 * @Copyright:
 */
public class XN631631 extends AProcessor{
	private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
	        .getBean(IProjectCorpInfoAO.class);
	
	private XN631631Req req = null;
	
	@Override
	public Object doBusiness() throws BizException {
		projectCorpInfoAO.dropProjectCorpInfo(req.getCode());
		return new BooleanRes(true);
	}

	@Override
	public void doCheck(String inputparams, String operator) throws ParaException {
		req = JsonUtil.json2Bean(inputparams,XN631631Req.class);
		ObjValidater.validateReq(req);
	}

}
