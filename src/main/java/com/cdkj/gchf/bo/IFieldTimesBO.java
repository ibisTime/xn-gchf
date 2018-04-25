package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.FieldTimes;
import com.cdkj.gchf.enums.EFieldType;

public interface IFieldTimesBO extends IPaginableBO<FieldTimes> {

    public void isFieldTimesExist(EFieldType fieldType, String userId);

    public void saveFieldTimes(EFieldType fieldType, String userId);

    public List<FieldTimes> queryFieldTimesList(FieldTimes condition);

}
