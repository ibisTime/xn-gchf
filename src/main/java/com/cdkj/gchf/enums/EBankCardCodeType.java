package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

public enum EBankCardCodeType {

    YINLIANSHANGWU("001", "银联商务（收单）"),

    ZHONGGUOYOUZHENGCHUXU("100", "中国邮政储蓄银行（收单）"),

    ZHONGGUOGONGSHANG("102", "中国工商银行"),

    ZHONGGUONONGYE("103", "中国农业银行"),

    ZHONGGUOYINHANG("104", "中国银行"),

    ZHONGGUOJIANSHE("105", "中国建设银行"),

    GUOJIAKAIFAYINHANG("201", "国家开发银行"),

    ZHONGGUOJINCHUKOU("202", "中国进出口银行"),

    ZHONGGUONONGYEFAZHAN("203", "中国农业发展银行"),

    JIAOTONG("301", "交通银行"),

    ZHONGXIN("302", "中信银行"),

    ZHONGGUOGUANGDA("303", "中国光大银行"),

    HUAXIA("304", "华夏银行"),

    ZHONGGUOMINSHENG("305", "中国民生银行"),

    GUANGDONGFAZHAN("306", "广东发展银行"),

    SHENZHENFAZHAN("307", "深圳发展银行"),

    ZHAOSHANG("308", "招商银行"),

    XINGYE("309", "兴业银行"),

    SHANGHAIPUTONGFAZHAN("310", "上海浦东发展银行"),

    SHANGHAIYINLIANSHANGWU("311", "上海银联商务/东莞商业银行"),

    CHENGSHISHANGYE("313", "城市商业银行"),

    NONGCUNSHANGYE("314", "农村商业银行（江苏）"),

    HENGFENG("315", "恒丰银行"),

    ZHESHANG("316", "浙商银行"),

    NONGCUNHEZUO("317", "农村合作银行"),

    BOHAIYINHANG("318", "渤海银行股份有限公司"),

    WEISHANG("319", "徽商银行股份有限公司"),

    ZHENYINHANG("320", "镇银行有限责任公司"),

    CHENGSHIXINYONGSHE("401", "城市信用社"),

    NONGCUNXINYONGSHE("402", "农村信用社（含北京农村商业银行）、东莞农信"),

    ZHONGGUOYOUZHENGCHUXUDAISHOUFU("403", "中国邮政储蓄银行（代收付）"),

    HUIFENG("501", "汇丰银行"),

    DONGFENG("502", "东亚银行"),

    NANYANGSHANGYE("503", "南洋商业银行"),

    HENGSHENGYINHANG("504", "恒生银行(中国)有限公司"), ZHONGGUOYINHANGXIANGGANG("505",
            "中国银行（香港）有限公司"),

    JIYOUYINHANG("506", "集友银行有限公司"),

    CHUANGXING("507", "创兴银行有限公司"),

    XINGZHANYINHANG("509", "星展银行（中国）有限公司"),

    YONGHENGYINHANG("510", "永亨银行（中国）有限公司"),

    YONGLONGYINHANG("512", "永隆银行"),

    HUAQIYINHANG("531", "花旗银行（中国）有限公司"),

    MEIGUOYINHANG("532", "美国银行有限公司"),

    MOGENDATONGYINHANG("533", "摩根大通银行(中国)有限公司"),

    SANLINGDONGJINGRILIANYINHANG("561", "三菱东京日联银行(中国）有限公司"),

    RIBENSANJINGZHUYOUYINHANG("563", "日本三井住友银行股份有限公司"),

    RUIHUISHIYEYINHANG("564", "瑞穗实业银行（中国）有限公司"),

    RIBENSHANKOUYINHANG("565", "日本山口银行股份有限公司"),

    YOULIYINHANG("593", "友利银行(中国)有限公司"),

    HANGUOWAIHUANYINHANG("591", "韩国外换银行股份有限公司"),

    HANGUOCHANYEYINHANG("594", "韩国产业银行"),

    XINHANYINHANG("595", "新韩银行(中国)有限公司"),

    HANGUOZHONGXIAOQIYE("596", "韩国中小企业银行有限公司"),

    HANYANYINHANG("597", "韩亚银行（中国）有限公司"),

    HUAQIAOYINHANG("621", "华侨银行（中国）有限公司"),

    DAHUAYINHANG("622", "大华银行（中国）有限公司"),

    TANGUOPANGU("631", "泰国盘谷银行(大众有限公司)"),

    AODILIZHONGYANGHEZUO("641", "奥地利中央合作银行股份有限公司"),

    BILISHILIANHEYINHANG("651", "比利时联合银行股份有限公司"),

    BILISHIFUTONGYINHANG("652", "比利时富通银行有限公司"),

    HELANYINHANG("661", "荷兰银行"),

    HELANANZHIYINHANG("662", "荷兰安智银行股份有限公司");

    EBankCardCodeType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EBankCardCodeType> getBankCardCodeMap() {
        Map<String, EBankCardCodeType> map = new HashMap<String, EBankCardCodeType>();
        for (EBankCardCodeType type : EBankCardCodeType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EBankCardCodeType getBankCardType(String code) {
        Map<String, EBankCardCodeType> map = getBankCardCodeMap();
        EBankCardCodeType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应银行卡类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EBankCardCodeType> map = getBankCardCodeMap();
        EBankCardCodeType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应银行卡类型不存在");
        }
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
