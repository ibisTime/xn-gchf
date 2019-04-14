package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cdkj.gchf.exception.BizException;

/**
 * 当前工种类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EWorkerType {

    QIWA("010", "砌筑工"),

    GANGJIN("020", "钢筋工"),

    JIAZI("030", "架子工"),

    HUNNINGTU("040", "混凝土工"),

    MOBAN("050", "模板工"),

    JIXIESHEBEIANZHUANG("060", "机械设备安装工"),

    TONGFENG("070", "通风工"),

    ANZHUANGQIZHONG("080", "安装起重工"),

    ANZHUANGQIAN("090", "安装钳工"),

    DIANZAISHEBEIANZHUANGTIAOSHI("100", "电气设备安装调试工"),

    GUANDAO("110", "管道工"),

    BIANDIANANZHUANG("120", "变电安装工"),

    JIANZHUDIAN("130", "建筑电工"),

    SIBENG("140", "司泵工"),

    WAJUECHANYUNZHUGONGJIXIESIJI("150", "挖掘铲运和桩工机械司机"),

    ZHUJICAOZUO("160", "桩机操作工"),

    QIZHONGXINHAO("170", "起重信号工"),

    JIANZHUQIZHONGJIXIEANZHUANGXIEWA("180", "建筑起重机械安装拆卸工"),

    ZHUANGSHIZHUANGXIU("190", "装饰装修工"),

    SHINEICHENGTAOSHEBEIANZHUANG("200", "室内成套设施安装工"),

    JIANZHUMENCHUANGANZHUANG("210", "建筑门窗幕墙安装工"),

    MUQIANGZHIZUO("220", "幕墙制作工"),

    FANGSHUI("230", "防水工"),

    MUGONG("240", "木工"),

    SHIGONG("250", "石工"),

    DIANHAN("270", "电焊工"),

    BAOPO("280", "爆破工"),

    CHUCHEN("290", "除尘工"),

    CESHIFANGXIAN("300", "测量放线工"),

    XIANLUJIASHE("310", "线路架设工"),

    GUJIANZHUCHUANTONGSHIGONG("320", "古建筑传统石工"),

    GUJIANZHUCHUANTONGWAGONG("330", "古建筑传统瓦工"),

    GUJIANZHUCHUANTONGCAISEGONG("340", "古建筑传统彩画工"),

    GUJIANZHUCHUANTONGMUGONG("350", "古建筑传统木工"),

    GUANLIRENYUAN("900", "管理人员");

    public static Map<String, EWorkerType> getWorkerTypeMap() {
        Map<String, EWorkerType> map = new HashMap<String, EWorkerType>();
        for (EWorkerType type : EWorkerType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EWorkerType getWorkerType(String code) {
        Map<String, EWorkerType> map = getWorkerTypeMap();
        EWorkerType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应工种类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EWorkerType> map = getWorkerTypeMap();
        EWorkerType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应工种类型不存在");
        }
    }

    public static String getWorkerTypeCode(String value) {
        Map<String, EWorkerType> workerTypeMap = getWorkerTypeMap();
        Set<Entry<String, EWorkerType>> entrySet = workerTypeMap.entrySet();
        for (Entry<String, EWorkerType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(value)) {
                return entry.getKey();
            }
        }
        throw new BizException("XN0000", "对应工人类型不存在");
    }

    EWorkerType(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
