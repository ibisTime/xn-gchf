package com.cdkj.gchf.ao;

import java.util.Map;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SYSConfig;

/**
 * @author old3
 */
public interface ISYSConfigAO {

    String DEFAULT_ORDER_COLUMN = "id";

    void editSYSConfig(Long id, String cvalue, String updater,
                       String remark);

    Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
                                            SYSConfig condition);

    SYSConfig getSYSConfig(Long id);

    Map<String, String> getSYSConfigMap(String type);

    SYSConfig getSYSConfig(String ckey);
}
