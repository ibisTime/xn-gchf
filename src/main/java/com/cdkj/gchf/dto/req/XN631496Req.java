package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查询记录
 * @author: nyc 
 * @since: 2018年5月21日 下午4:29:35 
 * @history:
 */
public class XN631496Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -9074797611278026742L;

    // 用户Id
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // （选填） 员工姓名
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
