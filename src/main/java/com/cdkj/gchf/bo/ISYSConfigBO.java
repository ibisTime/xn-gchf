/**
 * @Title ISYSConfigBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:52 
 * @version V1.0   
 */
package com.cdkj.gchf.bo;

import java.math.BigDecimal;
import java.util.Map;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.SYSConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:52 
 * @history:
 */
public interface ISYSConfigBO extends IPaginableBO<SYSConfig> {
    public int refreshSYSConfig(Long id, String cvalue, String updater,
            String remark);

    public SYSConfig getSYSConfig(Long id);

    public Map<String, String> getConfigsMap();

    public SYSConfig getSYSConfig(String key);

    public Map<String, String> getConfigsMap(String type);

    public String getStringValue(String key);

    public Double getDoubleValue(String key);

    public Integer getIntegerValue(String key);

    public Long getLongValue(String key);

    public BigDecimal getBigDecimalValue(String key);

}
