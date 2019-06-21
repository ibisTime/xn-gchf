package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.dto.req.*;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectWorker;

/**
 * @author old3
 */
@Component
public interface IProjectWorkerAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加项目人员
     */
    public String addProjectWorker(XN631690Req req);


    /**
     * H5端录入项目人员
     *
     * @return 主键code
     */
    String addProjectWorker(XN631696Req req);

    /**
     * 删除项目人员
     */
    public void dropProjectWorker(List<String> codeList);

    /**
     * 修改项目人员
     */
    public void editProjectWorker(XN631692Req req);

    /**
     * 导入项目人员
     * 1、根据证件查询人员实名制表信息
     * 1.1、如果表中没有这个数据 添加到实名制信息表中
     * 1.2、否则判断是否添加过该员工，没添加再添加
     */
    public void importProjectWorkers(XN631693Req req);

    /**
     * <p>Title: uploadProjectWorker</p>
     * <p>Description:上传项目人员到国家平台</p>
     */
    public void uploadProjectWorker(XN631694Req req);

    /**
     * <p>Title: updatePlantformProjectWorker</p>
     * <p>Description: 更新国家平台班组成员信息</p>
     */
    void updatePlantformProjectWorker(XN631695Req req);

    /**
     * 分页查
     */
    public Paginable<ProjectWorker> queryProjectWorkerPage(int start, int limit,
                                                           ProjectWorker condition);

    /**
     * <p>Title: queryProjectWorkerList</p>
     * <p>Description: 列表查班组人员-参数带deviceKey则查询班组人员授权情况</p>
     */
    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    /**
     * 根据code查 
     */
    public ProjectWorker getProjectWorker(String code);


    /**
     * 查询项目人员工种分布
     *
     * @param userId 用户id
     */
    List<Map> selectProjectWorkerWorkerTypeSpread(String userId);


    /**
     * 查询项目人员年龄分布
     *
     * @param userId 用户id
     */
    List<Map> selectWorkerAgeInterval(String userId);


    /**
     * 查询在职、今日上班、总发薪
     */
    List<Map> selectData(String userId);


    /**
     * 查询30天 入职 离职 考勤人数
     */
    Object select30DayData(String userId);

    /**
     * H5查询项目人员信息
     *
     * @param userId 用户id
     * @param code 项目人员编号
     * @return 项目人员
     */
    ProjectWorker queryProjectWorkerH5(String userId, String code);

    /****国家平台接口****/
    public void uploadProjectWorker(XN631911Req req);

    public void updateProjectWorker(XN631912Req req);

    public Paginable<ProjectWorker> queryProjectWorker(XN631913Req req);
}
