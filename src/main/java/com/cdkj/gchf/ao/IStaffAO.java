package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631413Req;
import com.cdkj.gchf.dto.req.XN631414Req;
import com.cdkj.gchf.dto.res.XN631094Res;

@Component
public interface IStaffAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 人员建档
    public String addStaff(XN631410Req req);

    // 拍摄免冠照
    public void editPict1(String code, String pic1, String feat,
            String updater);

    // 拍摄身份证正反面、手持身份证照片
    public void editPicts(XN631414Req req);

    // 录入联系方式信息
    public void editContactInfo(XN631413Req req);

    // 定时器每天更新员工特征值
    public void doUpdateFeatDaily();

    // 更新项目员工特征值
    public XN631094Res refreshFeat(String projectCode);

    // 根据项目获取特征值（匝机人脸识别设备使用）
    public String getStaffFeatList(String projectCode);

    // 获取员工所有信息（工资条/工作履历）
    public Staff getStaffInfo(String code, List<String> projetCodeList);

    // 根据关键字（姓名、身份证）获取员工信息
    public Staff getStaffByKeyword1(String keyword1,
            List<String> projetCodeList);

    public Paginable<Staff> queryStaffPage(int start, int limit,
            Staff condition);

    public List<Staff> queryStaffList(Staff condition);

    public Staff getStaff(String code);

}
