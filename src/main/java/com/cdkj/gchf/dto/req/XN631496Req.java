package com.cdkj.gchf.dto.req;

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

    // （选填） 员工姓名
    private String staffName;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

}
