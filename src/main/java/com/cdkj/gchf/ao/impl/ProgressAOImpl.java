package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.bo.IProgressBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Progress;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631380Req;
import com.cdkj.gchf.dto.req.XN631382Req;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;

@Service
public class ProgressAOImpl implements IProgressAO {

    @Autowired
    IProgressBO progressBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IUserBO userBO;

    @Override
    public String addProgress(XN631380Req req) {
        Progress data = new Progress();
        Project project = projectBO.getProject(req.getProjectCode());
        data.setCompanyCode(project.getCompanyCode());

        data.setProjectCode(req.getProjectCode());
        data.setDatetime(DateUtil.strToDate(req.getDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setDescription(req.getDescription());
        data.setPicture(req.getPicture());
        data.setUpdater(req.getUpdater());

        data.setRemark(req.getRemark());
        return progressBO.saveProgress(data);
    }

    @Override
    public int editProgress(XN631382Req req) {
        Progress data = progressBO.getProgress(req.getCode());
        data.setDatetime(DateUtil.strToDate(req.getDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setDescription(req.getDescription());
        data.setPicture(req.getPicture());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        return progressBO.refreshProgress(data);
    }

    @Override
    public Paginable<Progress> queryProgressPage(int start, int limit,
            Progress condition) {

        Paginable<Progress> page = new Page<Progress>();
        List<Progress> list = new ArrayList<Progress>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }

        // 补全信息
        page = progressBO.getPaginable(start, limit, condition);
        String updateName = null;
        Project project = null;
        for (Progress progress : page.getList()) {
            project = projectBO.getProject(progress.getProjectCode());
            updateName = getName(progress.getUpdater());
            progress.setUpdateName(updateName);
            progress.setProjectName(project.getName());
        }
        return page;
    }

    @Override
    public List<Progress> queryProgressList(Progress condition) {
        List<Progress> list = new ArrayList<Progress>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }
        // 补全信息
        list = progressBO.queryProgressList(condition);
        String updateName = null;
        Project project = null;
        for (Progress progress : list) {
            project = projectBO.getProject(progress.getProjectCode());
            updateName = getName(progress.getUpdater());
            progress.setUpdateName(updateName);
            progress.setProjectName(project.getName());
        }
        return list;
    }

    @Override
    public Progress getProgress(String code) {
        Progress data = progressBO.getProgress(code);
        String updateName = getName(data.getUpdater());
        data.setUpdateName(updateName);
        Project project = projectBO.getProject(data.getProjectCode());
        data.setProjectName(project.getName());
        return progressBO.getProgress(code);
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }
}
