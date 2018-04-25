package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBlacklistAO;
import com.cdkj.gchf.bo.IBlacklistBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Blacklist;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EBlacklistStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class BlacklistAOImpl implements IBlacklistAO {

    @Autowired
    private IBlacklistBO blacklistBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public void addBlacklist(String userId, String type, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户编号不存在");
        }
        Blacklist condition = new Blacklist();
        condition.setUserId(userId);
        condition.setType(type);
        condition.setStatus(EBlacklistStatus.VALID.getCode());
        if (blacklistBO.getTotalCount(condition) > 0) {
            throw new BizException("xn000000", "该用户已经在黑名单中");
        }
        blacklistBO.saveBlacklist(user, type, updater, remark);

    }

    @Override
    public int dropBlacklist(Long id, String updater, String remark) {
        int count = 0;
        Blacklist blacklist = blacklistBO.getBlacklist(id);
        if (EBlacklistStatus.VALID.getCode().equals(blacklist.getStatus())) {
            count = blacklistBO.removeBlacklist(id, updater, remark);
        } else {
            throw new BizException("xn000000", "黑名单记录不存在");
        }
        return count;
    }

    @Override
    public Paginable<Blacklist> queryBlacklistPage(int start, int limit,
            Blacklist condition) {
        Paginable<Blacklist> results = blacklistBO.getPaginable(start, limit,
            condition);
        for (Blacklist blacklist : results.getList()) {
            blacklist.setUser(userBO.getUser(blacklist.getUserId()));
        }
        return results;
    }

    @Override
    public List<Blacklist> queryBlacklistList(Blacklist condition) {
        return blacklistBO.queryBlacklistList(condition);
    }

    @Override
    public Blacklist getBlacklist(Long id) {
        return blacklistBO.getBlacklist(id);
    }

}
