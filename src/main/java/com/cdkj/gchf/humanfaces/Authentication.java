package com.cdkj.gchf.humanfaces;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Authentication {
    private AppConfig appConfig;

    {
        appConfig = AppConfig.getProjectConfig();
    }

    private String url = appConfig + "/Device/Authentication";

    public String getToken() {
        URL localURL;
        try {
            localURL = new URL(url);
            URLConnection connection = (URLConnection) localURL
                .openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    };
}
