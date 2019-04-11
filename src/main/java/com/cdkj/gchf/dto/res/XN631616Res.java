package com.cdkj.gchf.dto.res;

import java.util.List;

import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectBuilderLicense;

public class XN631616Res {
    private Project project;

    private List<ProjectBuilderLicense> license;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectBuilderLicense> getLicense() {
        return license;
    }

    public void setLicense(List<ProjectBuilderLicense> license) {
        this.license = license;
    }

}
