package com.cdkj.gchf.dto.res;

/**
 * 更新员工特征值
 * @author: silver 
 * @since: 2018年9月3日 下午5:21:46 
 * @history:
 */
public class XN631094Res {

    public XN631094Res(int count, String staffNames) {
        super();
        this.count = count;
        this.staffNames = staffNames;
    }

    // 更新员工数量
    private int count;

    // 更新员工姓名
    private String staffNames;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStaffNames() {
        return staffNames;
    }

    public void setStaffNames(String staffNames) {
        this.staffNames = staffNames;
    }

}
