package com.cdkj.gchf.api.impl;

import org.web3j.protocol.admin.methods.response.BooleanResponse;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;
/**
 * 
 * @ClassName:  XN631632   
 * @Description:修改参建单位
 * @author: Old3
 * @date:   2019年3月19日 下午10:47:31     
 * @Copyright:
 */
public class XN631632 extends AProcessor {
	private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder.getBean(IProjectCorpInfoAO.class);
	
	private XN631632Req req; 
	
	@Override
	public Object doBusiness() throws BizException {
		projectCorpInfoAO.editProjectCorpInfo(req);
		return new BooleanRes(true);
	}

	@Override
	public void doCheck(String inputparams, String operator) throws ParaException {
		req = JsonUtil.json2Bean(inputparams,XN631632Req.class);
		ObjValidater.validateReq(req);
	}

}
