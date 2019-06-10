package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.domain.BankCardInfo;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;
import com.cdkj.gchf.dto.req.XN631795Req;
import com.cdkj.gchf.dto.req.XN631797Req;

@Component
public interface IWorkerInfoAO {
    String DEFAULT_ORDER_COLUMN = "code";

    String addWorkerInfo(XN631790Req req);

    int addWorkerInfoIdCardInfo(XN631791Req req);

    int addWorkerInfoContact(XN631792Req req);

    /**
     * 根据身份证图片信息生成实名制信息,如果入参有实名制code 则刷新人员身份证信息
     * @param req 身份证正反面信息
     * @return 生成实名制code主键
     */
    String addOcrWorkerInfo(XN631795Req req);

    /**
     * H5端录入基本信息
     *
     * @param req
     * @return code
     */
    String refreshWorkerInfoH5(XN631797Req req);

    /**
     * base64方式 上传考勤照片到云端  保存云端返回的图片URL到本地
     * 根据workerinfo表中，人员上传状态和人员考勤照片状态处理
     * 已添加，删除重加 ，未添加，添加
     * @param code 主键code
     * @param attendancePicture 人员考勤照片(base64字符串)
     * @param userId 用户id
     */
    void refreshAttendancePicture(String code, String attendancePicture,
            String userId);

    /**
     * @param handIdCardImage 手持身份证照片
     */
    void refreshHandIdCardImage(String code, String handIdCardImage);

    /**
     * 分页查
     * @param userId yonghuid
     * @param start
     * @param limit
     * @param condition
     * @return
     */
    Paginable<WorkerInfo> queryWorkerInfoPage(String userId, int start,
            int limit, WorkerInfo condition);

    /**
     * 重新建档
     * @param req
     */
    void readdWorkerInfo(XN631793Req req);

    /**
     * 列表查人员实名制信息
     * @param condition  条件
     * @return List
     */
    List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition);

    /**
     * 根据主键code查询人员实名制信息
     * @param code 主键code
     * @return
     */
    WorkerInfo getWorkerInfo(String code);

    /**
     * 根据身份证号查询人员实名制信息
     * @param idCardNumber 身份证号
     * @return
     */
    WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber);

    /**
     * 查询项目人员银行卡信息
     *
     * @param workerCode 人员实名制code
     * @return List<BankCardInfo>
     */
    List<BankCardInfo> selectWorkerInfoByWorkerCode(String workerCode);

}
