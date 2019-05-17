package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631673Req;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;

/**
 * @author old3
 */
@Component
public interface IWorkerContractAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增劳动合同
     */
    String addWorkerContract(XN631670Req req);

    /**
     * 删除劳动合同
     */
    void dropWorkerContract(String userId, List<String> codeList);

    /**
     * 修改劳动合同
     */
    void editWorkerContract(XN631672Req data);

    /**
     * 分页查劳动合同
     */
    Paginable<WorkerContract> queryWorkerContractPage(int start,
                                                      int limit, WorkerContract condition);

    /**
     * 列表查劳动合同
     */
    List<WorkerContract> queryWorkerContractList(
            WorkerContract condition);

    /**
     * code查劳动合同
     */
    WorkerContract getWorkerContract(String code);

    /**
     * 导入劳动合同
     */
    void importWorkContractList(XN631673Req req);

    /**
     * 上传劳动合同
     */
    void uploadWorkContractList(String userId, List<String> codeList);

    /****国家平台接口****/
    void uploadWorkerContract(XN631916Req req);

    Paginable<WorkerContract> queryWorkerContract(XN631917Req req);

}
