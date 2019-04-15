package com.cdkj.gchf.humanfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

public class HttpRequest {
    private InputStream ins = null;

    private OutputStream ous = null;

    private String res = "";

    private InputStreamReader inputStreamReader = null;

    private BufferedReader bufferedReader = null;

    private HttpURLConnection httpURLConnection = null;

    public String doRequest(String url, String method,
            Map<String, String> req) {
        URL localURL = null;
        try {
            localURL = new URL(url);
            URLConnection connection = (URLConnection) localURL
                .openConnection();
            httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod(method);
            // 添加参数
            if (null != req) {
                Set<Entry<String, String>> entrySet = req.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    httpURLConnection.addRequestProperty(entry.getKey(),
                        entry.getValue());
                }
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            ins = httpURLConnection.getInputStream();
            String resString = null;
            inputStreamReader = new InputStreamReader(ins, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((resString = bufferedReader.readLine()) != null) {
                res += resString;
            }
            System.out.println("res:" + res);

            return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (ins != null) {
                    ins.close();
                }
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Test
    public void test1() {

        String url = AppConfig.getBaseUrl() + "/Device/Authentication";
        System.out.println(url);
        Map<String, String> reqParameter = new HashMap<>();
        reqParameter.put("appId", AppConfig.getAppId());
        reqParameter.put("appKey", AppConfig.getAppKey());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        reqParameter.put("timestamp", timestamp.toString());
        reqParameter
            .put("sign",
                Md5Crypt
                    .md5Crypt((AppConfig.getAppKey() + timestamp.toString()
                            + AppConfig.getAppSecret()).getBytes())
                .toLowerCase());
        String doRequest = doRequest(url, "POST", reqParameter);
        System.out.println(timestamp.toString());
        System.out
            .println(
                Md5Crypt
                    .md5Crypt((AppConfig.getAppKey() + timestamp.toString()
                            + AppConfig.getAppSecret()).getBytes())
                .toLowerCase());

        // System.out.println(timestamp.toString());
        System.out.println(doRequest);

    }
}
