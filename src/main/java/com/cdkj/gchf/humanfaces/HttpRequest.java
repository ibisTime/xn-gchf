package com.cdkj.gchf.humanfaces;

import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.MD5Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
//import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author old3
 */
public class HttpRequest {
    private static InputStream ins = null;

    private static OutputStream ous = null;

    private static String res = "";

    private static InputStreamReader inputStreamReader = null;

    private static BufferedReader bufferedReader = null;

    private static HttpURLConnection httpURLConnection = null;



    public static synchronized String doRequest(String url, String method,
            Map<String, String> req) {
        URL localURL;
        try {
            if (RequestMethod.GET.name().equals(method)) {
                url += "?";
                for (Entry<String, String> entry : req.entrySet()) {
                    url += entry.getKey() + "=" + entry.getValue() + "&";
                }
                url.substring(0, url.length() - 1);
                System.out.println(url);
            }
            localURL = new URL(url);
            URLConnection connection = (URLConnection) localURL
                .openConnection();
            httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod(method);
            // 维持长连接
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            StringBuffer sb = new StringBuffer();
            if (RequestMethod.POST.name().equals(method)) {
                httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);

                Set<Entry<String, String>> entrySet = req.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    sb.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
                httpURLConnection.connect();
                ous = httpURLConnection.getOutputStream();
                ous.write(
                    sb.substring(0, sb.length() - 1).getBytes());
                ous.flush();
            }
            ins = httpURLConnection.getInputStream();
            String resString;
            inputStreamReader = new InputStreamReader(ins, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((resString = bufferedReader.readLine()) != null) {
                res += resString;
            }
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
                res = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //    @Test
    public void test1() {

        String url = AppConfig.getBaseUrl() + "/Api/Device/Authentication";
        Map<String, String> reqParameter = new HashMap<>();
        reqParameter.put("appid", AppConfig.getAppid());
        reqParameter.put("appKey", AppConfig.getAppKey());
        Date time = DateUtil.strToDate(AppConfig.getTimestamp(),
            DateUtil.DATA_TIME_PATTERN_1);
        reqParameter.put("timestamp", String.valueOf(time.getTime()));
        String md5 = MD5Util.md5(AppConfig.getAppKey()
                + time.getTime() + AppConfig.getAppSecret());
        reqParameter.put("sign", md5.toLowerCase());
        String doRequest = doRequest(url, "POST", reqParameter);
        System.out.println(doRequest);

    }

}
