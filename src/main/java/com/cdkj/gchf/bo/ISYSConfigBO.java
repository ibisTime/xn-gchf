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

    public SYSConfig getSYSConfig(String ckey);

    public Map<String, String> getConfigsMap(String type);

    public String getStringValue(String ckey);

    public Double getDoubleValue(String ckey);

    public Integer getIntegerValue(String ckey);

    public Long getLongValue(String ckey);

    public BigDecimal getBigDecimalValue(String ckey);

}
