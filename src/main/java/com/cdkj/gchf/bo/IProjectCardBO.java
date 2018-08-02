package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectCard;

public interface IProjectCardBO extends IPaginableBO<ProjectCard> {
    public void saveProjectCard(String projectCode, String bankCode,
            String bankName, String accountName, String bankCardNumber,
            String subbranch, String updater, Date updateDatetime,
            String remark);

    public void refreshProjectCard(String code, String bankCode,
            String bankName, String accountName, String bankCardNumber,
            String subbranch, String updater, Date updateDatetime,
            String remark);

    public List<ProjectCard> queryProjectCardList(ProjectCard condition);

    public ProjectCard getProjectCard(String code);

    public ProjectCard getProjectCardByProject(String code);

}
