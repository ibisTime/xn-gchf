package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @Description:国家平台错误信息转换本地错误信息枚举类
 * @author: Old3
 * @date:   2019年4月18日 下午5:02:56     
 * @Copyright:
 */
public enum EGovErrorMessage {
    WorkerList("[headImage]不能为空!", "项目人员头像信息未上传,请重新建档补充信息后再上传"),

    WebSite("[webSite]格式不正确!", "企业基本信息中网站格式不正确,请检查修改后再上传"),

    PayMonthUnValid("[payMonth]不能大于今天!", "发放工资的年月不能大于今天,请删除重新添加"),

    DateUnValid("[date]不能大于当天!", "进退场日期不能大于今天"),

    HeadImageSize("[headImage]大小超过限制50KB!", "头像图片大小超过限制,请保持图片大小在50KB以内"),

    CorpExitTime("[exitTime]最晚为当前时间!", "退场时间最晚为当前时间"),

    BalanceDate("[balanceDate]发放日期不能大于今天", "工资发放日期最晚为今天,请修改后再上传"),

    TeamMasterNoUnValid("[teamSysNo]值超出范围或无效", "班组编号无效,请先上传班组信息"),

    HeadImageFormatUnValid("([headImage]格式无效,目前仅支持:jpg,jpeg,png,jpeg",
            "头像图片格式无效,目前仅支持:jpg,jpeg,png,jpeg"),

    PositiveImageFormatUnValid(
            "([positiveIDCardImage]格式无效,目前仅支持:jpg,jpeg,png,jpeg",
            "身份证正面照图片格式无效,目前仅支持:jpg,jpeg,png,jpeg"),

    NegativeImageFormatUnValid(
            "([negativeIDCardImage]格式无效,目前仅支持:jpg,jpeg,png,jpeg",
            "身份证反面照图片格式无效,目前仅支持:jpg,jpeg,png,jpeg"),

    TeamMasterNo("[teamSysNo]值超出范围或无效!", "班组信息未上传,请先上传班组信息"),

    AreaCode("[areaCode]参数值不正确,请参见行政区划字典表!", "注册地区编码无效,请修改"),

    JoinExitTimeUnValid("[date]不能小于历史进退场时间!", "进退场日期不能小于历史进退场信息");

    EGovErrorMessage(String govMessage, String localMessage) {
        this.govMessage = govMessage;
        this.localMessage = localMessage;
    }

    private String govMessage;

    private String localMessage;

    public String getGovMessage() {
        return govMessage;
    }

    public static Map<String, EGovErrorMessage> checkLocalMessage() {
        Map<String, EGovErrorMessage> map = new HashMap<>();
        EGovErrorMessage[] values = EGovErrorMessage.values();
        for (EGovErrorMessage message : values) {
            map.put(message.getGovMessage().toString(), message);
        }
        return map;
    }

    public static String getLocalMessageValue(String message) {
        Map<String, EGovErrorMessage> checkLocalMessage = checkLocalMessage();
        Set<Entry<String, EGovErrorMessage>> entrySet = checkLocalMessage
            .entrySet();
        for (Entry<String, EGovErrorMessage> entry : entrySet) {
            if (entry.getKey().contains(message)) {
                return entry.getValue().localMessage;
            }
        }
        return null;
    }

    public void setGovMessage(String govMessage) {
        this.govMessage = govMessage;
    }

    public String getLocalMessage() {
        return localMessage;
    }

    public void setLocalMessage(String localMessage) {
        this.localMessage = localMessage;
    }

}
