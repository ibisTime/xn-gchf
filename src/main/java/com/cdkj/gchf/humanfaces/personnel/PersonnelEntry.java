package com.cdkj.gchf.humanfaces.personnel;

import com.cdkj.gchf.humanfaces.AppConfig;

public class PersonnelEntry {
    private AppConfig appConfig;

    {
        appConfig = AppConfig.getProjectConfig();
    }

    public String doBussiness() {
        String url = appConfig.getBaseUrl() + "/Personnel/PersonnelEntry";
        String appId = appConfig.getAppId();
        String token = "";
        String name = "";
        String idNo = "";
        String phone = "";
        String tag = "";
        String type = "";

        return null;
    }

}
