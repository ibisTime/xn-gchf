/**
 * @Title SYSDictAOImpl.java 
 * @Package com.xnjr.moom.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:19:00 
 * @version V1.0   
 */
package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISYSDictAO;
import com.cdkj.gchf.bo.ISYSDictBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SYSDict;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631000Req;
import com.cdkj.gchf.enums.EDictType;
import com.cdkj.gchf.enums.EUser;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:19:00 
 * @history:
 */
@Service
public class SYSDictAOImpl implements ISYSDictAO {

    @Autowired
    ISYSDictBO sysDictBO;

    @Autowired
    IUserBO userBO;

    @Override
    public Long addSecondDict(XN631000Req req) {
        String parentKey = req.getParentKey();
        String key = req.getDkey();
        sysDictBO.checkKeys(parentKey, key);
        SYSDict sysDict = new SYSDict();
        sysDict.setType(EDictType.SECOND.getCode());
        sysDict.setParentKey(parentKey);
        sysDict.setDkey(key);
        sysDict.setDvalue(req.getDvalue());

        sysDict.setUpdater(getName(req.getUpdater()));
        sysDict.setUpdateDatetime(new Date());
        sysDict.setRemark(req.getRemark());

        return sysDictBO.saveSecondDict(sysDict);
    }

    @Override
    public void dropSYSDict(Long id) {
        sysDictBO.removeSYSDict(id);
    }

    @Override
    public void editSYSDict(Long id, String value, String updater,
            String remark) {
        sysDictBO.refreshSYSDict(id, value, getName(updater), remark);
    }

    @Override
    public Paginable<SYSDict> querySYSDictPage(int start, int limit,
            SYSDict condition) {
        Paginable<SYSDict> page = sysDictBO.getPaginable(start, limit,
            condition);
        for (SYSDict data : page.getList()) {
            data.setUpdateName(getName(data.getUpdater()));
        }
        return page;
    }

    @Override
    public List<SYSDict> querySysDictList(SYSDict condition) {
        List<SYSDict> list = sysDictBO.querySYSDictList(condition);
        for (SYSDict data : list) {
            data.setUpdateName(getName(data.getUpdater()));
        }
        return list;
    }

    @Override
    public SYSDict getSYSDict(Long id) {
        SYSDict data = sysDictBO.getSYSDict(id);
        data.setUpdateName(getName(data.getUpdater()));
        return data;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                // name = user.getRealName();
                name = user.getLoginName();
            }
        }
        return name;
    }
}
