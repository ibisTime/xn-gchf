package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631810Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class PayRollDetailAOImpl implements IPayRollDetailAO {
    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addPayRollDetail(PayRollDetail data) {
        return payRollDetailBO.savePayRollDetail(data);
    }

    @Override
    public int dropPayRollDetail(String code) {
        String uploadStatus = payRollDetailBO.getPayRollDetail(code)
            .getUploadStatus();

        if (uploadStatus != null & uploadStatus
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631811");
        }
        return payRollDetailBO.updatePayRollDetailDeleteStatus(code,
            EDeleteStatus.DELETED.getCode());
    }

    @Override
    public PayRollDetail getPayDetailRoll(String code) {
        return payRollDetailBO.getPayRollDetail(code);
    }

    @Override
    public Paginable<PayRollDetail> queryPayRollDetailPage(int start, int limit,
            PayRollDetail condition) {
        Paginable<PayRollDetail> page = payRollDetailBO.getPaginable(start,
            limit, condition);
        List<PayRollDetail> list = page.getList();
        for (PayRollDetail payRollDetail : list) {

            String payRollCode = payRollDetail.getPayRollCode();
            PayRoll payRoll = payRollBO.getPayRoll(payRollCode);
            if (payRoll == null)
                continue;
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(payRoll.getTeamSysNo());
            if (teamMaster != null) {
                String teamName = teamMasterBO
                    .getTeamMasterNameByTeamMasterSysNo(payRoll.getTeamSysNo());
                payRollDetail.setTeamName(teamName);
            }

            ProjectConfig configByProject = projectConfigBO
                .getProjectConfigByLocal(payRoll.getProjectCode());
            payRollDetail.setProjectName(configByProject.getProjectName());
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<PayRollDetail> queryPayRollDetailList(PayRollDetail condition) {
        return payRollDetailBO.queryList(condition);
    }

    @Override
    public int editPayRollDetail(XN631810Req req) {
        PayRollDetail payRollDetail = payRollDetailBO
            .getPayRollDetail(req.getCode());

        if (payRollDetail.getUploadStatus() != null
                & payRollDetail.getUploadStatus()
                    .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631810", "工资单已上传,不可修改");
        }
        return payRollDetailBO.updatePayRollDetail(req);
    }

}
