package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;

/**
 * @author
 */
@Component
public interface ICorpBasicinfoAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加企业基本信息
     *
     * @param req
     * @return
     */
    String addCorpBasicinfo(XN631250Req req);

    /**
     * 删除企业基本信息
     *
     * @param code 主键code
     * @return
     */
    int dropCorpBasicinfo(String code);

    /**
     * @param data 编辑企业基本信息
     */
    void editCorpBasicinfo(XN631251Req data);

    /**
     * 上传企业基本信息
     *
     * @param codeList 包含企业基本信息主键的list
     * @param userId   用户id
     */
    void uploadCorpBasicinfo(List<String> codeList, String userId);

    /**
     * @param userId    用户id
     * @param start     开始页
     * @param limit     每页数量
     * @param condition 条件
     * @return
     */
    Paginable<CorpBasicinfo> queryCorpBasicinfoPage(String userId,
                                                    int start, int limit, CorpBasicinfo condition);

    /**
     * 按条件查企业信息列表
     *
     * @param condition 条件
     * @return
     */
    List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition);

    /**
     * 根据code查企业信息
     *
     * @param code 主键code
     * @return
     */
    CorpBasicinfo getCorpBasicinfo(String code);


    /****国家平台接口****/
    void uploadCorpBasicinfo(XN631900Req data);

    /**
     * 查询国家平台企业基本信息
     *
     * @param req
     * @return
     */
    Paginable<CorpBasicinfo> queryCorpBasicinfo(XN631901Req req);
}
