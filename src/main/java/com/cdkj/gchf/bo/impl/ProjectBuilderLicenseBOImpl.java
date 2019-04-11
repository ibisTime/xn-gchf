package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBuilderLicenseBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectBuilderLicenseDAO;
import com.cdkj.gchf.domain.ProjectBuilderLicense;
import com.cdkj.gchf.dto.req.XN631600ReqLicenses;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProjectBuilderLicenseBOImpl
        extends PaginableBOImpl<ProjectBuilderLicense>
        implements IProjectBuilderLicenseBO {

    @Autowired
    private IProjectBuilderLicenseDAO projectBuilderLicenseDAO;

    @Override
    public void saveProjectBuilderLicense(String projectCode,
            List<XN631600ReqLicenses> builderLicenses) {

        if (CollectionUtils.isNotEmpty(builderLicenses)) {
            for (XN631600ReqLicenses reqLicenses : builderLicenses) {
                ProjectBuilderLicense projectBuilderLicense = new ProjectBuilderLicense();

                projectBuilderLicense.setCode(OrderNoGenerater
                    .generate(EGeneratePrefix.ProjectBuilderLicense.getCode()));
                projectBuilderLicense.setProjectCode(projectCode);
                projectBuilderLicense.setPrjName(reqLicenses.getPrjName());
                projectBuilderLicense
                    .setBuilderLicenseNum(reqLicenses.getBuilderLicenseNum());

                projectBuilderLicenseDAO.insert(projectBuilderLicense);
            }
        }
    }

    @Override
    public void removeByProject(String projectCode) {
        ProjectBuilderLicense condition = new ProjectBuilderLicense();

        condition.setProjectCode(projectCode);

        projectBuilderLicenseDAO.deleteByProject(condition);
    }

    @Override
    public List<ProjectBuilderLicense> queryProjectBuilderLicenseList(
            String projectCode) {
        ProjectBuilderLicense condition = new ProjectBuilderLicense();

        condition.setProjectCode(projectCode);

        return projectBuilderLicenseDAO.selectList(condition);
    }

    @Override
    public List<ProjectBuilderLicense> queryProjectBuilderLicenseList(
            ProjectBuilderLicense condition) {
        return projectBuilderLicenseDAO.selectList(condition);
    }

    @Override
    public ProjectBuilderLicense getProjectBuilderLicense(String code) {
        ProjectBuilderLicense data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectBuilderLicense condition = new ProjectBuilderLicense();
            condition.setCode(code);
            data = projectBuilderLicenseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "许可证编号不存在");
            }
        }
        return data;
    }

}
