package com.cdkj.gchf.ao;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.dto.req.XN631850Req;
import com.cdkj.gchf.dto.req.XN631852Req;
import com.cdkj.gchf.dto.res.XN631852Res;
import org.springframework.stereotype.Component;

import java.util.List;


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
     * @param  codeList 编号
     * @return int
     */
    public int dropProjectCamera(List<String> codeList);

    public int editProjectCamera(XN631852Req req);

    /**
     * 用户项目端摄像头rtsp地址
     *
     */
    Paginable<XN631852Res> getAllCameraRtspAddr(int start, int limit,
                                                ProjectCamera condition);

    /**
     * 释放hls资源
     *
     * @param userId
     */
    void releaseHlsResource(String userId);

    public Paginable<ProjectCamera> queryProjectCameraPage(int start, int limit,
            ProjectCamera condition);

    public List<ProjectCamera> queryProjectCameraList(ProjectCamera condition);

    public ProjectCamera getProjectCamera(String code);

}