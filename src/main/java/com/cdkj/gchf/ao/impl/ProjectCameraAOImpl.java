package com.cdkj.gchf.ao.impl;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCameraBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.FfmpegCache;
import com.cdkj.gchf.common.IpUtils;
import com.cdkj.gchf.common.PropertiesUtil;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631850Req;
import com.cdkj.gchf.dto.req.XN631852Req;
import com.cdkj.gchf.dto.res.XN631852Res;
import com.cdkj.gchf.enums.ECameraBitStream;
import com.cdkj.gchf.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectCameraAOImpl implements IProjectCameraAO {

    @Autowired
    private IProjectCameraBO projectCameraBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private FfmpegCache ffmpegCache;

    private String HLS_STREAM_DIR = PropertiesUtil.Config.HLS_STREAM_DIR;


    private String HLS_HOST = PropertiesUtil.Config.HLS_HOST;

    private String HLS_ENV = PropertiesUtil.Config.HLS_ENV;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        if (!IpUtils.checkIpValidation(req.getCameraIp())) {
            throw new BizException("XN00000", "Ip地址无效,请检查");
        }

        boolean b = projectCameraBO
                .checkCameraIpExist(briefUser.getOrganizationCode(), req.getCameraIp(), req.getCameraIpPort());
        if (b) {
            throw new BizException("XN00000", "已存在ip为:" + req.getCameraIp() + "的摄像头,请更改摄像头ip"
                    + "地址");
        }

        return projectCameraBO.saveProjectCamera(project, req, briefUser.getUserId());
    }

    @Override
    public int editProjectCamera(XN631852Req req) {
        String userId = req.getUserId();
        User briefUser = userBO.getBriefUser(userId);
        String organizationCode = briefUser.getOrganizationCode();
        Project project = projectBO.getProject(organizationCode);
        if (project == null) {
            throw new BizException("XN000000", "项目不存在");
        }

        ProjectCamera projectCamera = new ProjectCamera();
        BeanUtils.copyProperties(req, projectCamera);
        projectCamera.setProjectCode(project.getCode());
        projectCamera.setProjectName(project.getName());

        return projectCameraBO.refreshProjectCamera(projectCamera);
    }

    @Override
    public Paginable<XN631852Res> getAllCameraRtspAddr(int start, int limit,
                                                       ProjectCamera condition) {
        User briefUser = userBO.getBriefUser(condition.getUserId());
        if (briefUser == null) {
            throw new BizException("XN00000", "用户不存在");
        }

        condition.setProjectCode(briefUser.getOrganizationCode());
        Paginable<ProjectCamera> page = projectCameraBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            Paginable<XN631852Res> resPaginable = new Page<>(start, limit, page.getTotalCount());

            List<ProjectCamera> projectCameras = page.getList();

            List<XN631852Res> res = new ArrayList<>();

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            for (ProjectCamera projectCamera : projectCameras) {
                res.add(new XN631852Res(projectCamera.getCameraName(), getHlsStream(briefUser.getUserId(), uuid, projectCamera), uuid));
            }

            resPaginable.setList(res);

            return resPaginable;

        }

        return null;
    }

    @Override
    public void releaseHlsResource(String userId, String uuid) {
        ffmpegCache.releaseFfmpegResource(userId, uuid);
    }

    private String getHlsStream(String userId, String uuid, ProjectCamera projectCamera) {

        String rtspUrl = wrapRtspUrl(projectCamera);

        String folderName = projectCamera.getCameraIp() + "." + projectCamera.getCameraIpPort();
        String folderPath = HLS_STREAM_DIR + File.separator + HLS_ENV + File.separator + folderName;

        String ffmpegCommand = wrapFfmpegCommand(rtspUrl, folderPath);

        if (null == ffmpegCache.checkFfmpegCommand(ffmpegCommand)) {

            try {

                File file = new File(folderPath);
                File m3u8File = new File(folderPath + File.separator + "test.m3u8");
                if (m3u8File.exists()) {
                    FileUtils.deleteDirectory(file);
                }
                file.mkdir();

                Runtime.getRuntime().exec("touch " + folderPath + File.separator + "test.m3u8");
                Process process = Runtime.getRuntime().exec(ffmpegCommand);

                ffmpegCache.handleOutputStream(process);

                ffmpegCache.storeFfmpegCommand(userId, uuid, process, ffmpegCommand);

                logger.info("ffmpeg命令：" + ffmpegCommand);

            } catch (IOException e) {
                logger.error("摄像头转码失败", e);
            }

        } else {
            ffmpegCache.storeFfmpegCommand(userId, uuid, ffmpegCommand);
        }

        return HLS_HOST + "/" + HLS_ENV + "/" + folderName + "/test.m3u8";
    }

    @Override
    public int dropProjectCamera(List<String> codeList) {
        for (String code : codeList) {
            projectCameraBO.removeProjectCamera(code);
        }
        return 1;
    }

    private String wrapRtspUrl(ProjectCamera projectCamera) {
        String baseUrl = "rtsp://";
        StringBuffer sb = new StringBuffer();

        sb.append(baseUrl)
                .append(projectCamera.getCameraUsername())
                .append(":")
                .append(projectCamera.getCameraPassword())
                .append("@")
                .append(projectCamera.getCameraIp());

        if (StringUtils.isNotBlank(projectCamera.getCameraIpPort())) {
            sb.append(":").append(projectCamera.getCameraIpPort());
        }
        sb.append("/").append(projectCamera.getCameraChannel());
        sb.append("/").append(projectCamera.getCameraBitStream());

        return sb.toString();
    }

    private String wrapFfmpegCommand(String rtspUrl, String folderPath) {
        StringBuilder ffmpegCommand = new StringBuilder();

        ffmpegCommand.append("ffmpeg ")
                .append(" -f rtsp ")
                .append(" -rtsp_transport tcp ")
                .append(" -i ").append(rtspUrl)
                .append(" -vcodec libx264 ")
                .append(" -preset ultrafast ")
                .append(" -tune zerolatency ")
                .append(" -hls_time 6 ")
                .append(" -hls_list_size 5 ")
                .append(" -hls_wrap 10 ")
                .append(folderPath + File.separator + "test.m3u8");

        return ffmpegCommand.toString();
    }

    @Override
    public Paginable<ProjectCamera> queryProjectCameraPage(int start, int limit,
            ProjectCamera condition) {

        User briefUser = userBO.getBriefUser(condition.getUserId());
        if (briefUser != null) {
            condition.setProjectCode(briefUser.getOrganizationCode());
        }

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
