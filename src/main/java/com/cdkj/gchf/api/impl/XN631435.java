package com.cdkj.gchf.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631435Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631435 extends AProcessor {

    private IMessageAO messageAO = SpringContextHolder
        .getBean(IMessageAO.class);

    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631435Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Message condition = new Message();
        condition.setProjectCode(req.getProjectCode());
        condition.setProjectName(req.getProjectName());
        condition.setKind(req.getKind());
        condition.setSender(req.getSender());

        condition.setHandler(req.getHandler());
        condition.setStatus(req.getStatus());
        condition.setKeyword(req.getKeyword());
        condition.setStatusList(req.getStatusList());
        condition.setBankName(req.getBankName());

        condition.setSubbranch(req.getSubbranch());
        condition.setProjectCodeList(req.getProjectCodeList());
        condition.setMonth(req.getMonth());

        // 缓兵之计，前端应该穿projectCodeList
        if (null != req.getCompanyCode()) {
            Project project = new Project();
            project.setCompanyCode(req.getCompanyCode());
            List<Project> projectList = projectAO.queryProjectList(project);
            List<String> projectCodeList = new ArrayList<String>();
            if (CollectionUtils.isNotEmpty(projectList)) {
                for (Project data : projectList) {
                    projectCodeList.add(data.getCode());
                }
            }
            condition.setProjectCodeList(projectCodeList);
        }

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMessageAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return messageAO.queryMessagePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631435Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
