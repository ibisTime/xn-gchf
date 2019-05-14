package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.dto.req.XN631830Req;

/**
 * @author
 */
@Component
public interface IEquipmentWorkerAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加设备人员 -> 单设备-多人员的情况。讲本地人员信息与设备关联。即人员授权。
     * 先取消所有已授权人员授权，重新授权人员信息到云端。
     * @param req
     */
    void addEquipmentWorker(XN631830Req req);

    /**
     * 分页查询设备人员信息
     * @param start 开始页
     * @param limit 每页记录数
     * @param condition 条件
     * @return 设备列表
     */
    Paginable<EquipmentWorker> queryEquipmentWorkerPage(int start,
                                                        int limit, EquipmentWorker condition);

    /**
     * @param condition 按条件查询设备人员列表信息
     * @return 设备人员列表
     */
    List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition);

    /**
     * @param code 根据主键code查询设备人员信息
     * @return 设备人员
     */
    EquipmentWorker getEquipmentWorker(String code);

}
