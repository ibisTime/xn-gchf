package com.cdkj.gchf.ao;

import java.util.Map;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SYSConfig;

public interface ISYSConfigAO {

    String DEFAULT_ORDER_COLUMN = "id";

    public void editSYSConfig(Long id, String cvalue, String updater,
            String remark);

    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition);

    public SYSConfig getSYSConfig(Long id);

    public Map<String, String> getSYSConfigMap(String type);

    public SYSConfig getSYSConfig(String ckey);
}
