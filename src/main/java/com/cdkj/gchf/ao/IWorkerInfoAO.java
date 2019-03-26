package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;

@Component
public interface IWorkerInfoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWorkerInfo(XN631790Req req);

    public int dropWorkerInfo(String code);

    public int editWorkerInfo(XN631791Req data);

    public Paginable<WorkerInfo> queryWorkerInfoPage(int start, int limit,
            WorkerInfo condition);

    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition);

    public WorkerInfo getWorkerInfo(String code);

}
