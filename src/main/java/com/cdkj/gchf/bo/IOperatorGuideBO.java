package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.OperatorGuide;

/**
 * 操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午4:50:09 
 * @history:
 */
public interface IOperatorGuideBO extends IPaginableBO<OperatorGuide> {

    // 保存操作指南
    public String saveOperatorGuide(OperatorGuide data);

    // 删除操作指南
    public void removeOperatorGuide(String code);

    // 修改操作指南
    public void refreshOperatorGuide(OperatorGuide data);

    public List<OperatorGuide> queryOperatorGuideList(OperatorGuide condition);

    public OperatorGuide getOperatorGuide(String code);

}
