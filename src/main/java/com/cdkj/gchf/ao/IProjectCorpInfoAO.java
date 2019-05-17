package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633Req;
import com.cdkj.gchf.dto.req.XN631635Req;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;

/**
 * @author old3
 */
@Component
public interface IProjectCorpInfoAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * @Description: 新增参建
     */
    String addProjectCorpInfo(XN631630Req data);

    /**
     * @Description: 删除参建单位
     */
    void dropProjectCorpInfo(List<String> codeList);

    /**
     * @Description: 修改参建单位
     */
    void editProjectCorpInfo(XN631632Req req);

    /**
     * @Description: 导入参建单位
     */
    void importProjectCorpInfo(XN631633Req req);

    /**
     * @Description: 上传到国家平台
     */
    void uploadProjectCorpInfo(String userId, List<String> codes);

    /**
     * 修改国家平台
     */
    void updatePlantformProjectCorpInfo(XN631635Req req);

    /****国家平台接口****/
    void uploadProjectCorpInfo(XN631905Req data);

    /**
     * 修改平台（200版本
     */
    void updateProjectCorpInfo(XN631906Req req);

    /**
     * 查询国家平台参建单位信息
     */
    Paginable<ProjectCorpInfo> queryProjectCorpInfo(XN631907Req req);

    /**
     * 以下为本地查询
     */
    ProjectCorpInfo getProjectCorpInfo(String code);

    Paginable<ProjectCorpInfo> queryProjectCorpInfoPage(int start,
                                                        int limit, ProjectCorpInfo condition);

    List<ProjectCorpInfo> queryProjectCorpInfoList(
            ProjectCorpInfo condition);

}
