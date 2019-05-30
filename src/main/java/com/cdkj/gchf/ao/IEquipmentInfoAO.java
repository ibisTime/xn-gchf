package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.dto.req.XN631821Req;

/**
 * @author
 */
@Component
public interface IEquipmentInfoAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加考勤设备到云端，同时添加到本地数据库
     * @param req 参数
     * @return 主键
     */
    String addEquipmentInfo(XN631820Req req);


    /**
     * 修改设备信息，同时同步到云端
     * @param req
     */
    void modifyEquipment(XN631821Req req);

    /**
     * 启用考勤设备
     * @param deviceKey 设备序列号
     * @param userId 用户id
     */
    void enableEquipment(String deviceKey, String userId);

    /**
     * 禁用设备
     * @param deviceKey 设备序列号
     * @param userId 用户id
     */
    void disableEquipment(String deviceKey, String userId);


    /**
     * @param deviceKey 设备序列号
     * @param code 设备code
     */
    void delEquipment(String code, String deviceKey);
    /**
     * 分页查询设备信息，同时查询云端设备。返回
     * @param start 开始页
     * @param limit 每页数量
     * @param condition 条件
     * @return 分页查云端最新的考勤设备信息
     */
    Paginable<EquipmentInfo> queryEquipmentInfoPage(int start, int limit,
                                                    EquipmentInfo condition);

    /**
     * 列表查设备列表
     * @param condition 条件
     * @return 本地设备信息列表
     */
    List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition);

    /**
     * 查询设备详情
     * @param userId 用户id
     * @param code 主键code
     * @return
     */
    EquipmentInfo getEquipmentInfo(String userId, String code);

}
