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

    /**
     * <p>Title: addEquipmentInfo</p>   
     * <p>Description: 添加考勤设备</p>   
     */
    String addEquipmentInfo(XN631820Req req);

    /**
     * 删除设备 
     */
    public int dropEquipmentInfo(String code);

    // 修改设备
    void modifyEquipment(XN631821Req req);

    /**
     * 启用设备 
     */
    public void enableEquipment(String deviceKey, String userId);

    /**
     * 禁用设备 
     */
    public void disableEquipment(String deviceKey, String userId);

    public Paginable<EquipmentInfo> queryEquipmentInfoPage(int start, int limit,
            EquipmentInfo condition);

    public List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition);

    // 添加设备
    public EquipmentInfo getEquipmentInfo(String userId, String code);

}
