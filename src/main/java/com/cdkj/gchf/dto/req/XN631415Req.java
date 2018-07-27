package com.cdkj.gchf.dto.req;

/**
 * 分页查务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631415Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 624217987942773996L;

    // （选填）证件类型
    private String idType;

    // （选填）关键字
    private String keyword;

    // （选填）更新人
    private String updater;

    // （选填）身份证号
    private String idNo;

    // 雇佣省份（监管端查询时使用）
    private String province;

    // 雇佣城市（监管端查询时使用）
    private String city;

    // 雇佣区域（监管端查询时使用）
    private String area;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
