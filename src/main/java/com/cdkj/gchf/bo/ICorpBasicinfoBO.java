package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;

public interface ICorpBasicinfoBO extends IPaginableBO<CorpBasicinfo> {

    String saveCorpBasicinfo(XN631250Req req);


    int removeCorpBasicinfo(String code);

    void refreshCorpBasicinfo(XN631251Req data);

    /**
     * 修改企业基本信息上传状态
     *
     * @param code         主键code
     * @param uploadStatus 上传状态
     */
    void refreshUploadStatus(String code, String uploadStatus);

    List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition);

    CorpBasicinfo getCorpBasicinfo(String code);

    CorpBasicinfo getCorpBasicinfoByCorp(String corpCode);

    /****国家平台接口****/
    void doUpload(XN631900Req req, ProjectConfig projectConfig);

    Paginable<CorpBasicinfo> doQuery(XN631901Req req,
                                     ProjectConfig projectConfig);
}
