package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

public class Company extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -6888451800753133637L;

    private String code;// 编号

    private String name; // 名称

    private Date createDatetime; // 创建时间

    // ***********db********
    private String keyword;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
