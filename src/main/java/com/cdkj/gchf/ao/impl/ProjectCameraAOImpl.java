package com.cdkj.gchf.ao.impl;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.IpUtils;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631850Req;
import com.cdkj.gchf.dto.res.XN631852Res;
import com.cdkj.gchf.enums.ECameraBitStream;
import com.cdkj.gchf.enums.EUserKind;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.bo.IProjectCameraBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.exception.BizException;


//CHECK ��鲢��ע��
@Service
public class ProjectCameraAOImpl implements IProjectCameraAO {

    @Autowired
    private IProjectCameraBO projectCameraBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addProjectCamera(XN631850Req req) {

        User briefUser = userBO.getBriefUser(req.getUserId());

        ECameraBitStream.checkExists(req.getCameraBitStream());

        if (briefUser == null) {
            throw new BizException("XN00000", "用户不存在");
        }
        Project project = projectBO.getProject(briefUser.getOrganizationCode());
        if (project == null) {
            throw new BizException("XN00000", "项目不存在");
        }
        boolean b = projectCameraBO
                .checkCameraIpExist(briefUser.getOrganizationCode(), req.getCameraIp());
        if (b) {
            throw new BizException("XN00000", "已存在ip为:" + req.getCameraIp() + "的摄像头,请更改摄像头ip"
                    + "地址");
        }
        if (!IpUtils.checkIpValidation(req.getCameraIp())) {
            throw new BizException("XN00000", "Ip地址无效,请检查");
        }

        return projectCameraBO.saveProjectCamera(project, req, briefUser.getUserId());
    }

    @Override
    public int editProjectCamera(ProjectCamera data) {
        if (!projectCameraBO.isProjectCameraExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return projectCameraBO.refreshProjectCamera(data);
    }

    @Override
    public List<XN631852Res> getAllCameraRtspAddr(String userId) {
        String baseUrl = "rtsp://";
        User briefUser = userBO.getBriefUser(userId);
        if (briefUser == null) {
            throw new BizException("XN00000", "用户不存在");
        }
        if (briefUser.getType().equals(EUserKind.Owner.getCode())) {
            List<ProjectCamera> projectCameras = projectCameraBO
                    .selectProjectCamera(briefUser.getOrganizationCode());
            List<XN631852Res> res = new ArrayList<>();
            for (int i = 0; i < projectCameras.size(); i++) {
                ProjectCamera projectCamera = projectCameras.get(i);
                XN631852Res xn631852Res = new XN631852Res();
                xn631852Res.setCameraName(projectCamera.getCameraName());
                StringBuffer sb = new StringBuffer();
                sb.append(baseUrl)
                        .append(projectCamera.getCvrHostUsername())
                        .append(":")
                        .append(projectCamera.getCvrHostPassword())
                        .append("@")
                        .append(projectCamera.getCameraIp());

                if (StringUtils.isNotBlank(projectCamera.getCameraIpPort())) {
                    sb.append(":").append(projectCamera.getCameraIpPort());
                }
                sb.append("/").append(projectCamera.getCameraChannel());
                sb.append("/").append(projectCamera.getCameraBitStream());
                xn631852Res.setRpstpStreamAddress(sb.toString());
                res.add(xn631852Res);
            }
            return res;
        }
        return null;
    }

    @Override
    public int dropProjectCamera(String code) {
        if (!projectCameraBO.isProjectCameraExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return projectCameraBO.removeProjectCamera(code);
    }

    @Override
    public Paginable<ProjectCamera> queryProjectCameraPage(int start, int limit,
            ProjectCamera condition) {
        return projectCameraBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProjectCamera> queryProjectCameraList(ProjectCamera condition) {
        return projectCameraBO.queryProjectCameraList(condition);
    }

    @Override
    public ProjectCamera getProjectCamera(String code) {
        return projectCameraBO.getProjectCamera(code);
    }
}