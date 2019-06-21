package com.cdkj.gchf.bo;

import com.cdkj.gchf.domain.BankCardInfo;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631810Req;
import com.cdkj.gchf.dto.req.XN631812ReqData;
import com.google.gson.JsonObject;

public interface IPayRollDetailBO extends IPaginableBO<PayRollDetail> {
    /**
     * 保存工资明细 
     */
    public void savePayRollDetail(String payRollCode, String teamSysNo,
            String projectCode, String getPayMonth,
            List<XN631770ReqDetail> data);

    /**
     * 保存工资明细 
     */
    public String savePayRollDetail(PayRollDetail payRollDetail);

    /**
     * 保存工资明细 
     */
    public String savePayRollDetail(ProjectWorker projectWorker,
            String payRollcode, XN631812ReqData xn631773ReqData, BankCardInfo bankCardInfo);

    /**
     * 根据工资单编号删除工资单明细 
     */
    int fakeDeletePayRollDetailByPayRollCode(String payRollCode);

    /**
     * 根据身份证号删除工资单明细 
     */
    int fakeDeletePayRollDetail(String idCardType, String idCardNumber,
            String projectCode);

    /**
     * 删除 
     */
    public int deletePayRollDetail(String code);

    /**
     * 修改工资明细 
     */
    public int updatePayRollDetail(XN631772Req req);

    /**
     * 修改工资明细 
     */
    public int updatePayRollDetail(XN631810Req data);

    /**
     * 修改工资上传状态
     */
    void refreshUploadStatus(String code, String uploadStatus);

    /**
     * 修改删除状态
     */
    int updatePayRollDetailDeleteStatus(String code, String status);

    public List<PayRollDetail> queryList(PayRollDetail condition);

    public List<PayRollDetail> queryListByPayRollDetail(String payRollCode);

    public PayRollDetail getPayRollDetail(String code);

    public List<PayRollDetail> getPayRollDetailByPayRollCode(
            String payRollCode);

    /**
     * 查询人员工资单条数
     */
    long selectCountByWorkerCode(String workerCode);

    /**
     * 查询项目人员最新一条记录
     *
     * @param workerCode 项目人员code
     * @return data
     */
    PayRollDetail getLastPayRollData(String workerCode);
    /**
     * 获取上传国家平台的json 
     */
    public JsonObject getUploadRequestJsontoPlantform(PayRoll payRollData,
            TeamMaster teamMasterData, ProjectConfig projectConfigData,
            PayRollDetail payRollDetailData);

}
