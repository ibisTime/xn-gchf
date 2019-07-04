package com.cdkj.gchf.ocr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.HttpUtils;
import com.cdkj.gchf.common.PropertiesUtil;
import com.cdkj.gchf.enums.EOcrSide;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : silver
 * @since : 2019-05-15 17:31
 */
@Component
public class OcrConnecter {
    public static final String OCR_HOST = PropertiesUtil.Config.OCR_HOST;

    public static final String OCR_PATH = PropertiesUtil.Config.OCR_PATH;

    public static final String OCR_APP_KEY = PropertiesUtil.Config.OCR_APP_KEY;

    public static final String OCR_APP_SECRET = PropertiesUtil.Config.OCR_APP_SECRET;

    public static final String OCR_APP_CODE = PropertiesUtil.Config.OCR_APP_CODE;

    public static String getOrcData(String image, String side) {
        // 如果文档的输入中含有inputs字段，设置为True， 否则设置为False
        Boolean is_old_format = false;

        JSONObject configObj = new JSONObject();
        configObj.put("side", side);
        String config_str = configObj.toString();

        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + OCR_APP_CODE);

        Map<String, String> querys = new HashMap<String, String>();

        // 对图像进行base64编码
        String imgBase64 = image;

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        try {
            if (is_old_format) {
                JSONObject obj = new JSONObject();
                obj.put("image", getParam(50, imgBase64));
                if (config_str.length() > 0) {
                    obj.put("configure", getParam(50, config_str));
                }
                JSONArray inputArray = new JSONArray();
                inputArray.add(obj);
                requestObj.put("inputs", inputArray);
            } else {
                requestObj.put("image", imgBase64);
                if (config_str.length() > 0) {
                    requestObj.put("configure", config_str);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String bodys = requestObj.toString();

        try {
            HttpResponse response = HttpUtils.doPost(OCR_HOST, OCR_PATH, method,
                headers, querys, bodys);
            int stat = response.getStatusLine().getStatusCode();
            if (stat != 200) {
                System.out.println("Http code: " + stat);
                System.out.println("http header error msg: "
                        + response.getFirstHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg:"
                        + EntityUtils.toString(response.getEntity()));
                return null;
            }

            String res = EntityUtils.toString(response.getEntity());
            JSONObject res_obj = JSON.parseObject(res);
            if (is_old_format) {
                JSONArray outputArray = res_obj.getJSONArray("outputs");
                String output = outputArray.getJSONObject(0)
                    .getJSONObject("outputValue").getString("dataValue");
                JSONObject out = JSON.parseObject(output);
                System.out.println(out.toJSONString());
                return out.toString();
            } else {
                System.out.println(res_obj.toJSONString());
                return res_obj.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * 获取参数的json对象
     */
    public static JSONObject getParam(int type, String dataValue) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String args[]) {
        String image = ""
            .replace("data:image/jpeg;base64,", "");
        String orcData = getOrcData(image, EOcrSide.FACE.getCode());
        System.out.println("orcData = " + orcData);
    }
}
