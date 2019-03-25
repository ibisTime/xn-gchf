package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.impl.PayRollDetailDAOImpl;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.enums.EGeneratePrefix;

@Service(value = "payRollDetailBO")
public class PayRollDetailBOImpl extends PaginableBOImpl<PayRollDetail>
        implements IPayRollDetailBO {
    @Autowired
    private PayRollDetailDAOImpl payRollDetailDAO;

    @Override
    public void savePayRollDetail(String projectCode,
            List<XN631770ReqDetail> data) {
        for (XN631770ReqDetail xn631770ReqDetail : data) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRollDetail.getCode());
            PayRollDetail payRollDetail = new PayRollDetail();
            payRollDetail.setCode(code);
            payRollDetail.setPayRollCode(projectCode);
            BeanUtils.copyProperties(xn631770ReqDetail, payRollDetail);
            payRollDetailDAO.insert(payRollDetail);
        }
    }

    @Override
    public long getTotalCount(PayRollDetail condition) {
        return 0;
    }

    @Override
    public Paginable<PayRollDetail> getPaginable(int start,
            PayRollDetail condition) {
        return null;
    }

    @Override
    public Paginable<PayRollDetail> getPaginable(int start, int pageSize,
            PayRollDetail condition) {
        return null;
    }

    @Override
    public int deletePayRollDetail(String payRollcode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollcode);
        return payRollDetailDAO.delete(payRollDetail);
    }

    @Override
    public int updatePayRollDetail(PayRollDetail data) {
        return 0;
    }

    @Override
    public List<PayRollDetail> queryListByPayRoll(String payRollCode) {
        PayRollDetail payRollDetail = new PayRollDetail();

        payRollDetail.setPayRollCode(payRollCode);

        return payRollDetailDAO.selectList(payRollDetail);
    }

}
