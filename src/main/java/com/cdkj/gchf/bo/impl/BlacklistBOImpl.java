package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBlacklistBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IBlacklistDAO;
import com.cdkj.gchf.domain.Blacklist;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EBlacklistStatus;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.exception.BizException;

@Component
public class BlacklistBOImpl extends PaginableBOImpl<Blacklist>
        implements IBlacklistBO {

    @Autowired
    private IBlacklistDAO blacklistDAO;

    @Override
    public Long saveBlacklist(User user, String type, String updater,
            String remark) {
        Long id = null;
        if (user != null && StringUtils.isNotBlank(user.getUserId())) {
            Date now = new Date();
            Blacklist data = new Blacklist();
            data.setUserId(user.getUserId());
            data.setType(type);
            data.setStatus(EBlacklistStatus.VALID.getCode());
            data.setCreateDatetime(now);
            data.setUpdater(updater);
            data.setUpdateDatetime(now);
            data.setRemark(remark);
            blacklistDAO.insert(data);
            // 对应 mapper insert方法中必须配置 useGeneratedKeys="true" keyProperty="id"
            id = data.getId();
        }
        return id;
    }

    @Override
    public int removeBlacklist(Long id, String updater, String remark) {
        int count = 0;
        if (id != null) {
            Blacklist data = new Blacklist();
            data.setId(id);
            data.setStatus(EBlacklistStatus.DELETE.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            count = blacklistDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<Blacklist> queryBlacklistList(Blacklist condition) {
        return blacklistDAO.selectList(condition);
    }

    @Override
    public Blacklist getBlacklist(Long id) {
        Blacklist data = null;
        if (id != null) {
            Blacklist condition = new Blacklist();
            condition.setId(id);
            data = blacklistDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "黑名单记录不存在");
            }
        }
        return data;
    }

    @Override
    public String isAddBlacklist(String userId) {
        String flag = EBoolean.NO.getCode();
        Blacklist condition = new Blacklist();
        condition.setUserId(userId);
        condition.setStatus(EBlacklistStatus.VALID.getCode());
        if (getTotalCount(condition) > 0) {
            flag = EBoolean.YES.getCode();
        }
        return flag;
    }
}
