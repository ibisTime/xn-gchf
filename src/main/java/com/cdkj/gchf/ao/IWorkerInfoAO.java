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
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWorkerInfo(XN631790Req req);

    public int addWorkerInfoIdCardInfo(XN631791Req req);

    public int addWorkerInfoContact(XN631792Req req);

    public int dropWorkerInfo(String code);

    public Paginable<WorkerInfo> queryWorkerInfoPage(int start, int limit,
            WorkerInfo condition);

    public void readdWorkerInfo(XN631793Req req);

    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition);

    public WorkerInfo getWorkerInfo(String code);

    public WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber);

}
