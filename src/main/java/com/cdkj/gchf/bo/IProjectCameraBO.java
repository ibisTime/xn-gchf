package com.cdkj.gchf.bo;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.dto.req.XN631850Req;

import java.util.List;


public interface IProjectCameraBO extends IPaginableBO<ProjectCamera> {


    public boolean isProjectCameraExist(String code);

    /**
     * 校验ip是否重复
     */
    boolean checkCameraIpExist(String projectCode, String ip, String port);

    /**
     * 校验名称是否存在
     */
    boolean checkCameraName(String projectCode, String name);

    /**
     *
     */
    public String saveProjectCamera(Project project, XN631850Req req, String userId);


    public int removeProjectCamera(String code);


    public int refreshProjectCamera(ProjectCamera data);


    public List<ProjectCamera> queryProjectCameraList(ProjectCamera condition);


    public ProjectCamera getProjectCamera(String code);

    /**
     * 获取项目端摄像头
     *
     * @param projectCode 项目编号
     */
    List<ProjectCamera> selectProjectCamera(String projectCode);


}