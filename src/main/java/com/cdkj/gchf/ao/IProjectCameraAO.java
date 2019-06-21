package com.cdkj.gchf.ao;

import com.cdkj.gchf.dto.req.XN631850Req;
import com.cdkj.gchf.dto.res.XN631852Res;
import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCamera;
import org.springframework.stereotype.Component;


//CHECK ��鲢��ע��
@Component
public interface IProjectCameraAO {

    String DEFAULT_ORDER_COLUMN = "code";


    /**
     * 添加摄像头
     *
     * @param req 入参
     * @return code
     */
    public String addProjectCamera(XN631850Req req);

    /***
     * @author old3
     * @date 2019-06-21
     * @param  code 编号
     * @return int
     */
    public int dropProjectCamera(String code);

    public int editProjectCamera(ProjectCamera data);

    /**
     * 用户项目端摄像头rtsp地址
     *
     * @param userId 用户id
     */
    List<XN631852Res> getAllCameraRtspAddr(String userId);

    public Paginable<ProjectCamera> queryProjectCameraPage(int start, int limit,
            ProjectCamera condition);

    public List<ProjectCamera> queryProjectCameraList(ProjectCamera condition);

    public ProjectCamera getProjectCamera(String code);

}