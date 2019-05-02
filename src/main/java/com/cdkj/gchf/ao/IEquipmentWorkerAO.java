package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.dto.req.XN631830Req;

@Component
public interface IEquipmentWorkerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addEquipmentWorker(EquipmentWorker data);

    public int dropEquipmentWorker(String code);

    public int editEquipmentWorker(EquipmentWorker data);

    public Paginable<EquipmentWorker> queryEquipmentWorkerPage(int start,
            int limit, EquipmentWorker condition);

    public List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition);

    public EquipmentWorker getEquipmentWorker(String code);

    void addEquipmentWorker(XN631830Req req);
}
