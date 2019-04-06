package com.cdkj.gchf.dto.req;

import com.cdkj.gchf.domain.ProjectConfig;
import com.google.gson.JsonObject;

public class XN631694ReqData {
    private String teamMasterCode;

    private JsonObject requestJson;

    private ProjectConfig projectConfig;

    public String getTeamMasterCode() {
        return teamMasterCode;
    }

    public void setTeamMasterCode(String teamMasterCode) {
        this.teamMasterCode = teamMasterCode;
    }

    public JsonObject getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(JsonObject requestJson) {
        this.requestJson = requestJson;
    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

}
