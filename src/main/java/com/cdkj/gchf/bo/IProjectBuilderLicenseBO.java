package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectBuilderLicense;
import com.cdkj.gchf.dto.req.XN631600ReqLicenses;

public interface IProjectBuilderLicenseBO
        extends IPaginableBO<ProjectBuilderLicense> {

    public void saveProjectBuilderLicense(String projectCode,
            List<XN631600ReqLicenses> builderLicenses);

    public void removeByProject(String projectCode);

    public List<ProjectBuilderLicense> queryProjectBuilderLicenseList(
            ProjectBuilderLicense condition);

    public ProjectBuilderLicense getProjectBuilderLicense(String code);

}
