package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.ICorpBasicinfoAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;

@Service
public class CorpBasicinfoAOImpl implements ICorpBasicinfoAO {

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addCorpBasicinfo(XN631250Req req) {

        if (null != corpBasicinfoBO.getCorpBasicinfoByCorp(req.getCorpCode())) {
            throw new BizException("XN631250", "统一社会信用代码已存在，请勿重复添加");
        }

        String code = corpBasicinfoBO.saveCorpBasicinfo(req);

        return code;
    }

    @Override
    public void editCorpBasicinfo(XN631251Req req) {
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfo(req.getCode());

        if (!req.getCorpCode().equals(corpBasicinfo.getCorpCode())
                && null != corpBasicinfoBO
                    .getCorpBasicinfoByCorp(req.getCorpCode())) {
            throw new BizException("XN631250", "统一社会信用代码已存在，请勿重复添加");
        }

        corpBasicinfoBO.refreshCorpBasicinfo(req);
    }

    @Override
    public int dropCorpBasicinfo(String code) {
        return corpBasicinfoBO.removeCorpBasicinfo(code);
    }

    @Override
    public void uploadCorpBasicinfo(String code) {
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO.getCorpBasicinfo(code);

        GovConnecter.getGovData("Corp.Upload",
            JSONObject.toJSONString(corpBasicinfo));
    }

    @Override
    public void syncCorpBasicinfo(String code) {

    }

    @Override
    public void uploadCorpBasicinfo(XN631900Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631900", "该项目未配置，无法上传");
        }

        corpBasicinfoBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<CorpBasicinfo> queryCorpBasicinfo(XN631901Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631900", "该项目未配置，无法查询");
        }

        return corpBasicinfoBO.doQuery(req, projectConfig);
    }

    @Override
    public Paginable<CorpBasicinfo> queryCorpBasicinfoPage(int start, int limit,
            CorpBasicinfo condition) {
        return corpBasicinfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition) {
        return corpBasicinfoBO.queryCorpBasicinfoList(condition);
    }

    @Override
    public CorpBasicinfo getCorpBasicinfo(String code) {
        return corpBasicinfoBO.getCorpBasicinfo(code);
    }

}
