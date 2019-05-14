package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.dto.req.XN631830Req;

@Component
public interface IEquipmentWorkerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * <p>Title: addEquipmentWorker</p>   
     * <p>Description:添加设备人员 -> 单设备-多人员的情况</p>   
     */
    void addEquipmentWorker(XN631830Req req);

    public Paginable<EquipmentWorker> queryEquipmentWorkerPage(int start,
            int limit, EquipmentWorker condition);

    public List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition);

    public EquipmentWorker getEquipmentWorker(String code);

}
