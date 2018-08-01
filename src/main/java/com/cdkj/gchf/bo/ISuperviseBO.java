package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Supervise;

/**
 * 监管单位
 * @author: silver 
 * @since: 2018年8月1日 上午10:34:44 
 * @history:
 */
public interface ISuperviseBO extends IPaginableBO<Supervise> {

    public String saveSupervise(String province, String city, String area);

    public List<Supervise> querySuperviseList(Supervise condition);

    public Supervise getSupervise(String code);

    public Supervise getSupervise(String province, String city, String area);

}
