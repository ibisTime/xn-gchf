package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;

@Component
public interface IWorkerInfoAO {
    String DEFAULT_ORDER_COLUMN = "code";

    String addWorkerInfo(XN631790Req req);

    int addWorkerInfoIdCardInfo(XN631791Req req);

    int addWorkerInfoContact(XN631792Req req);


    /**
     * base64方式 上传考勤照片到云端  保存云端返回的图片URL到本地
     * 根据workerinfo表中，人员上传状态和人员考勤照片状态处理
     * 已添加，删除重加 ，未添加，添加
     *
     * @param code              主键code
     * @param attendancePicture 人员考勤照片(base64字符串)
     * @param userId            用户id
     */
    void refreshAttendancePicture(String code, String attendancePicture,
                                  String userId);

    /**
     * 分页查
     *
     * @param userId    yonghuid
     * @param start
     * @param limit
     * @param condition
     * @return
     */
    Paginable<WorkerInfo> queryWorkerInfoPage(String userId, int start,
                                              int limit, WorkerInfo condition);

    /**
     * 重新建档
     *
     * @param req
     */
    void readdWorkerInfo(XN631793Req req);

    /**
     * 列表查人员实名制信息
     *
     * @param condition 条件
     * @return List
     */
    List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition);

    /**
     * 根据主键code查询人员实名制信息
     *
     * @param code 主键code
     * @return
     */
    WorkerInfo getWorkerInfo(String code);

    /**
     * 根据身份证号查询人员实名制信息
     *
     * @param idCardNumber 身份证号
     * @return
     */
    WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber);

}
