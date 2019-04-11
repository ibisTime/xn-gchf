package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IPayRollDetailDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.PayRollDetail;

@Repository("payRollDetailDAOImpl")
public class PayRollDetailDAOImpl extends AMybatisTemplate
        implements IPayRollDetailDAO {

    @Override
    public int insert(PayRollDetail data) {
        return super.insert(NAMESPACE.concat("insert_PayRollDetail"), data);
    }

    @Override
    public int delete(PayRollDetail data) {
        return super.delete(NAMESPACE.concat("delete_PayRollDetail"), data);
    }

    @Override
    public PayRollDetail select(PayRollDetail condition) {
        return super.select(NAMESPACE.concat("select_PayRollDetail"), condition,
            PayRollDetail.class);
    }

    @Override
    public long selectTotalCount(PayRollDetail condition) {
        return 0;
    }

    @Override
    public List<PayRollDetail> selectList(PayRollDetail condition) {
        return super.selectList(NAMESPACE.concat("select_PayRollDetail"),
            condition, PayRollDetail.class);
    }

    @Override
    public List<PayRollDetail> selectList(PayRollDetail condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_PayRollDetail"), start,
            count, condition, PayRollDetail.class);
    }

    @Override
    public int update(PayRollDetail payRollDetail) {
        return super.update(NAMESPACE.concat("update_PayRollDetail"),
            payRollDetail);
    }

    @Override
    public int updateStatus(PayRollDetail payRollDetail) {
        return super.update(NAMESPACE.concat("update_PayRollDetail_status"),
            payRollDetail);
    }

    @Override
    public int updatePayRollCode(PayRollDetail payRollDetail) {
        return super.update(
            NAMESPACE.concat("update_PayRollDetail_payRollCode"),
            payRollDetail);
    }

    @Override
    public int updatePayRollDetailDeleteStatus(PayRollDetail payRollDetail) {
        return super.update(
            NAMESPACE.concat("update_PayRollDetail_delete_status"),
            payRollDetail);
    }
}
