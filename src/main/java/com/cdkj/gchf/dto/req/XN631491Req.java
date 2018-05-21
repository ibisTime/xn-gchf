package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除查询记录
 * @author: nyc 
 * @since: 2018年5月21日 下午4:29:35 
 * @history:
 */
public class XN631491Req {

    // 编号
    @NotEmpty(message = "编号不能为空")
    private List<String> codeList;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
