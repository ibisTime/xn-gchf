package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.dto.req.XN631821Req;

@Component
public interface IEquipmentInfoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addEquipmentInfo(EquipmentInfo data);

    String addEquipmentInfo(XN631820Req req);

    public int dropEquipmentInfo(String code);

    public int editEquipmentInfo(EquipmentInfo data);

    public void enableEquipment(String deviceKey, String userId);

    public void disableEquipment(String deviceKey, String userId);

    public Paginable<EquipmentInfo> queryEquipmentInfoPage(int start, int limit,
            EquipmentInfo condition);

    public List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition);

    // 添加设备
    public EquipmentInfo getEquipmentInfo(String userId, String code);

    // 修改设备
    void modifyEquipment(XN631821Req req);

}
