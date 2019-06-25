package com.cdkj.gchf.bo.impl;

import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631850Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectCameraBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectCameraDAO;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.exception.BizException;


//CHECK ��鲢��ע��
@Component
public class ProjectCameraBOImpl extends PaginableBOImpl<ProjectCamera> implements
        IProjectCameraBO {

    @Autowired
    private IProjectCameraDAO projectCameraDAO;

    @Override
    public boolean isProjectCameraExist(String code) {
        ProjectCamera condition = new ProjectCamera();
        condition.setCode(code);
        if (projectCameraDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCameraIpExist(String projectCode, String ip) {
        ProjectCamera projectCamera = new ProjectCamera();
        projectCamera.setCameraIp(ip);
        projectCamera.setProjectCode(projectCode);
        long l = projectCameraDAO.selectTotalCount(projectCamera);
        if (l > 1L) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCameraName(String projectCode, String name) {
        ProjectCamera projectCamera = new ProjectCamera();
        projectCamera.setCameraName(name);
        projectCamera.setProjectCode(projectCode);
        long l = projectCameraDAO.selectTotalCount(projectCamera);
        if (l > 1L) {
            return true;
        }
        return false;
    }

    @Override
    public String saveProjectCamera(Project project, XN631850Req req, String userId) {
        ProjectCamera projectCamera = new ProjectCamera();
        String code = OrderNoGenerater.generate(EGeneratePrefix.ProjectCamera.getCode());

        BeanUtils.copyProperties(req, projectCamera);
        projectCamera.setProjectCode(project.getCode());
        projectCamera.setProjectName(project.getName());

        projectCamera.setUpdater(userId);
        projectCamera.setUpdateDatetime(
                new Date(System.currentTimeMillis()));
        projectCamera.setCode(code);
        projectCameraDAO.insert(projectCamera);
        return code;
    }


    @Override
    public int removeProjectCamera(String code) {
        ProjectCamera projectCamera = new ProjectCamera();
        projectCamera.setCode(code);
        return projectCameraDAO.delete(projectCamera);
    }

    @Override
    public int refreshProjectCamera(ProjectCamera data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = projectCameraDAO.update(data);
        }
        return count;
    }

    @Override
    public List<ProjectCamera> queryProjectCameraList(ProjectCamera condition) {
        return projectCameraDAO.selectList(condition);
    }

    @Override
    public ProjectCamera getProjectCamera(String code) {
        ProjectCamera data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectCamera condition = new ProjectCamera();
            condition.setCode(code);
            data = projectCameraDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

    @Override
    public List<ProjectCamera> selectProjectCamera(String projectCode) {
        ProjectCamera projectCamera = new ProjectCamera();
        projectCamera.setProjectCode(projectCode);
        return projectCameraDAO.selectList(projectCamera);
    }
}