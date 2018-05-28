package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.AbnormalRemind;

public interface IAbnormalRemindBO extends IPaginableBO<AbnormalRemind> {

    void saveAbnormalRemin(AbnormalRemind data);

    AbnormalRemind getAbnormalRemind(String code);

    void removeAbnormalRemind(AbnormalRemind data);

    void refreshAbnormalRemind(AbnormalRemind data);

    List<AbnormalRemind> queryAbnormalRemindList(AbnormalRemind condition);

}
