package com.cdkj.gchf.ao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISYSConfigAO;
import com.cdkj.gchf.bo.ISYSConfigBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SYSConfig;

@Service
public class SYSConfigAOImpl implements ISYSConfigAO {
    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public void editSYSConfig(Long id, String cvalue, String updater,
            String remark) {
        sysConfigBO.refreshSYSConfig(id, cvalue, updater, remark);
    }

    @Override
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition) {
        return sysConfigBO.getPaginable(start, limit, condition);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        return sysConfigBO.getSYSConfig(id);
    }

    @Override
    public SYSConfig getSYSConfig(String ckey) {
        return sysConfigBO.getSYSConfig(ckey);
    }

    @Override
    public Map<String, String> getSYSConfigMap(String type) {
        return sysConfigBO.getConfigsMap(type);
    }
}
