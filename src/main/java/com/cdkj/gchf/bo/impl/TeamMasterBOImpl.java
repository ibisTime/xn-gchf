package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ITeamMasterDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631653ReqData;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class TeamMasterBOImpl extends PaginableBOImpl<TeamMaster>
        implements ITeamMasterBO {

    @Autowired
    private ITeamMasterDAO teamMasterDAO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String saveTeamMaster(XN631650Req data,
            CorpBasicinfo corpBasicinfo) {
        TeamMaster teamMasterInfo = new TeamMaster();
        BeanUtils.copyProperties(data, teamMasterInfo);
        if (StringUtils.isNotBlank(data.getEntryTime())) {
            Date enrtyTime = DateUtil.strToDate(data.getEntryTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            teamMasterInfo.setEntryTime(enrtyTime);
        }
        if (StringUtils.isNotBlank(data.getExitTime())) {
            Date exitTime = DateUtil.strToDate(data.getExitTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            teamMasterInfo.setExitTime(exitTime);
        }
        if (StringUtils.isNotBlank(data.getResponsiblePersonIdcardType())) {
            EIdCardType.checkExists(data.getResponsiblePersonIdcardType());
            teamMasterInfo.setResponsiblePersonIdcardType(
                data.getResponsiblePersonIdcardType());
        }
        if (StringUtils.isNotBlank(data.getTeamLeaderIdcardType())) {
            EIdCardType.checkExists(data.getTeamLeaderIdcardType());
            teamMasterInfo
                .setTeamLeaderIdcardType(data.getTeamLeaderIdcardType());
        }

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.TeamMaster.getCode());
        teamMasterInfo.setCode(code);
        teamMasterInfo.setCorpName(corpBasicinfo.getCorpName());
        teamMasterInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());

        teamMasterDAO.insert(teamMasterInfo);
        return code;
    }

    @Override
    public void removeTeamMaster(String userId, String code) {
        TeamMaster data = new TeamMaster();
        data.setCode(code);
        teamMasterDAO.delete(data);
    }

    @Override
    public void refreshTeamMaster(XN631652Req data) {
        TeamMaster teamMaster = new TeamMaster();
        BeanUtils.copyProperties(data, teamMaster);

        if (StringUtils.isNotBlank(data.getEntryTime())) {
            Date entryTime = DateUtil.strToDate(data.getEntryTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            teamMaster.setEntryTime(entryTime);
        }
        if (StringUtils.isNotBlank(data.getExitTime())) {
            Date exitTime = DateUtil.strToDate(data.getExitTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            teamMaster.setExitTime(exitTime);
        }
        if (StringUtils.isNotBlank(data.getResponsiblePersonIDCardType())) {
            String responsibleIdCardType = EIdCardType
                .getIdCardDictValue(data.getResponsiblePersonIDCardType());
            teamMaster.setResponsiblePersonIdcardType(responsibleIdCardType);
        }

        teamMasterDAO.update(teamMaster);
    }

    @Override
    public void refreshUploadStatus(String code, String uploadStatus) {
        TeamMaster teamMaster = new TeamMaster();

        teamMaster.setCode(code);
        teamMaster.setUploadStatus(uploadStatus);

        teamMasterDAO.updateUploadStatus(teamMaster);
    }

    @Override
    public void refreshTeamSysNoByLocal(String code, String teamSysNo) {
        TeamMaster teamMaster = new TeamMaster();

        teamMaster.setCode(code);
        teamMaster.setTeamSysNo(teamSysNo);

        teamMasterDAO.updateTeamSysNo(teamMaster);
    }

    @Override
    public void doUpload(XN631908Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getResponsiblePersonIdNumber())) {
            req.setResponsiblePersonIdNumber(AesUtils.encrypt(
                req.getResponsiblePersonIdNumber(), projectConfig.getSecret()));
        }

        if (StringUtils.isNotBlank(req.getTeamLeaderIdNumber())) {
            req.setTeamLeaderIdNumber(AesUtils.encrypt(
                req.getTeamLeaderIdNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("Team.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public void doUpdate(XN631909Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getResponsiblePersonIdNumber())) {
            req.setResponsiblePersonIdNumber(AesUtils.encrypt(
                req.getResponsiblePersonIdNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("Team.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<TeamMaster> doQuery(XN631910Req req,
            ProjectConfig projectConfig) {
        TeamMaster teamMaster = new TeamMaster();
        BeanUtils.copyProperties(req, teamMaster);

        String data = JSONObject.toJSON(teamMaster).toString();

        String queryString = GovConnecter.getGovData("Team.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<TeamMaster> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, TeamMaster.class);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (TeamMaster master : page.getList()) {
                if (StringUtils
                    .isNotBlank(master.getResponsiblePersonIdNumber())) {
                    master.setResponsiblePersonIdNumber(
                        AesUtils.decrypt(master.getResponsiblePersonIdNumber(),
                            projectConfig.getSecret()));
                }
            }
        }

        return page;
    }

    @Override
    public List<TeamMaster> queryTeamMasterList(TeamMaster condition) {
        return teamMasterDAO.selectList(condition);
    }

    @Override
    public TeamMaster getTeamMaster(String code) {
        TeamMaster data = null;
        if (StringUtils.isNotBlank(code)) {
            TeamMaster condition = new TeamMaster();
            condition.setCode(code);
            data = teamMasterDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "班组编号不存在");
            }
        }
        return data;
    }

    @Override
    public TeamMaster getTeamMasterByCondition(TeamMaster condition) {
        return teamMasterDAO.select(condition);
    }

    @Override
    public void saveTeamMasterByImport(XN631653Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        for (XN631653ReqData xn631654ReqData : req.getDateList()) {

            EIdCardType
                .checkExists(xn631654ReqData.getResponsiblePersonIdcardType());
            String code = null;
            TeamMaster teamMaster = new TeamMaster();
            teamMaster.setCorpCode(xn631654ReqData.getCorpCode());
            String responsidCardType = EIdCardType.getIdCardDictValue(
                xn631654ReqData.getResponsiblePersonIdcardType());
            teamMaster.setResponsiblePersonIdcardType(responsidCardType);
            teamMaster.setProjectCode(req.getProjectCode());
            BeanUtils.copyProperties(xn631654ReqData, teamMaster);
            teamMasterDAO.insert(teamMaster);

            code = OrderNoGenerater
                .generate(EGeneratePrefix.TeamMaster.getValue());
            operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
                code, EOperateLogOperate.UploadTeamMaster.getValue(), user,
                "导入班组信息");
        }

    }

    @Override
    public String saveTeamMaster(TeamMaster teamMaster) {
        String code = null;
        code = OrderNoGenerater.generate(EGeneratePrefix.TeamMaster.getCode());
        teamMaster.setCode(code);
        teamMasterDAO.insert(teamMaster);
        return code;
    }

}
