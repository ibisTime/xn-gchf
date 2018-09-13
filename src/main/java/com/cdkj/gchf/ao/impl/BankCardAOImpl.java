package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631420Req;
import com.cdkj.gchf.dto.req.XN631422Req;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class BankCardAOImpl implements IBankCardAO {

    @Autowired
    private IBankCardBO bankCardBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IEmployBO employBO;

    @Override
    public String addBankCard(XN631420Req req) {
        BankCard data = bankCardBO.isBankCardExist(req.getEmployCode());
        if (null != data) {
            throw new BizException("XN000", "该员工已有工资卡，请勿重复添加！");
        }

        return bankCardBO.addBankCard(req);
    }

    @Override
    public void editBankCard(XN631422Req req) {
        bankCardBO.refreshBankCard(req.getCode(), req.getBankCode(),
            req.getBankName(), req.getSubbranch(), req.getBankcardNumber(),
            req.getUpdater(), req.getRemark());
    }

    @Override
    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition) {
        Paginable<BankCard> page = new Page<BankCard>();
        page = bankCardBO.getPaginable(start, limit, condition);
        for (BankCard bankCard : page.getList()) {
            initBankcard(bankCard);
        }
        return page;
    }

    @Override
    public List<BankCard> queryBankCardList(BankCard condition) {
        List<BankCard> list = new ArrayList<BankCard>();
        list = bankCardBO.queryBankCardList(condition);
        for (BankCard bankCard : list) {
            initBankcard(bankCard);
        }
        return list;
    }

    @Override
    public BankCard getBankCard(String code) {
        BankCard data = bankCardBO.getBankCard(code);
        initBankcard(data);
        return data;
    }

    private void initBankcard(BankCard data) {
        data.setUpdateName(getName(data.getUpdater()));

        // 银行全称：银行名称+支行名称
        if (data != null && null != data.getBankName()
                && null != data.getSubbranch()) {
            data.setBankSubbranchName(
                data.getBankName().concat(data.getSubbranch()));
        }

        // 员工信息
        Staff staff = staffBO.getStaffBrief(data.getStaffCode());
        if (null != staff) {
            data.setIdNo(staff.getIdNo());
            data.setStaffMobile(staff.getMobile());
        }

        // 部门职位
        Employ employ = employBO.getEmploy(data.getEmployCode());
        if (null != employ) {
            data.setPosition(employ.getPosition());
            data.setDepartmentName(employ.getDepartmentName());
        }
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }
}
