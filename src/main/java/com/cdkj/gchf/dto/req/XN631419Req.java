package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取该务工人员所有信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631419Req {

    // 编号
    @NotBlank(message = "不能为空")
    private String code;

    private List<String> projetCodeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getProjetCodeList() {
        return projetCodeList;
    }

    public void setProjetCodeList(List<String> projetCodeList) {
        this.projetCodeList = projetCodeList;
    }

}
