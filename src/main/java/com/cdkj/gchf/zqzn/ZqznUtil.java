package com.cdkj.gchf.zqzn;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.Base64Util;
import com.cdkj.gchf.common.OkHttpUtils;
import com.cdkj.gchf.common.PropertiesUtil;
import com.cdkj.gchf.common.QiniuUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.EBizErrorCode;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Component
public class ZqznUtil {

    public static String SECRET_KEY = "887DE27B914988C9CF7B2DEE15E3EDF8";

    public static String URL_ID_CARD_FRONT_OCR = "https://face.zhiquplus.com/api/faceid/1.0/{app_key}/id_card_front_ocr";

    public static String URL_ID_CARD_BACK_OCR = "https://face.zhiquplus.com/api/faceid/1.0/{app_key}/id_card_back_ocr";

    public static String URL_IREAL_AUTH = "https://face.zhiquplus.com/api/faceid/1.0/{app_key}/real_auth";

    public static String IMAGE_FILE_DIR = PropertiesUtil.Config.IMAGE_FILE_DIR;

    public static String ZQZN_APP_KEY = PropertiesUtil.Config.ZQZN_APP_KEY;

    public static ZqznInfoFront getOcrFrontInfo(String base64) {

        ZqznInfoFront zqznInfoFront = null;
        base64 = base64.replace("data:image/jpeg;base64,", "").replace(" ",
            "+");

        File file = null;
        try {

            file = Base64Util.base64ToFile(base64,
                OrderNoGenerater.generate("Image") + ".jpg", IMAGE_FILE_DIR);

            RequestBody fileBody = RequestBody
                .create(MediaType.parse("image/png"), file);
            RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), fileBody)
                .addFormDataPart("return_face_img", "1")
                .addFormDataPart("trace_id", OrderNoGenerater.generate("ZQZN"))
                .build();
            String json = OkHttpUtils.doMultipartPost(
                getZqznRequestUrl(URL_ID_CARD_FRONT_OCR), requestBody);

            JSONObject jsonObject = JSONObject.parseObject(json);

            if (jsonObject.getBoolean("success")) {
                zqznInfoFront = JSONObject.parseObject(
                    jsonObject.getJSONObject("data").toJSONString(),
                    ZqznInfoFront.class);
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "身份证正面信息读取失败，请联系服务商！");
            }

        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "身份证正面信息读取发送异常，" + e.getMessage());
        }

        return zqznInfoFront;

    }

    public static ZqznInfoBack getOcrBackInfo(String base64) {

        ZqznInfoBack zqznInfoBack = null;
        base64 = base64.replace("data:image/jpeg;base64,", "").replace(" ",
            "+");

        File file = null;
        try {

            file = Base64Util.base64ToFile(base64,
                OrderNoGenerater.generate("Image") + ".jpg", IMAGE_FILE_DIR);

            RequestBody fileBody = RequestBody
                .create(MediaType.parse("image/png"), file);

            RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), fileBody)
                .addFormDataPart("trace_id", OrderNoGenerater.generate("ZQZN"))
                .build();

            String json = OkHttpUtils.doMultipartPost(
                getZqznRequestUrl(URL_ID_CARD_BACK_OCR), requestBody);

            JSONObject jsonObject = JSONObject.parseObject(json);

            if (jsonObject.getBoolean("success")) {
                zqznInfoBack = JSONObject.parseObject(
                    jsonObject.getJSONObject("data").toJSONString(),
                    ZqznInfoBack.class);
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "身份证反面信息读取失败，请联系服务商！");
            }

        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "身份证反面信息读取发送异常，" + e.getMessage());
        }

        return zqznInfoBack;

    }

    public ZqznInfoRealAuth getRealAuthInfo(String idNo, String realName,
            String faceImage) {

        String imageUrl = getDomainUrl(faceImage);

        ZqznInfoRealAuth zqznInfoRealAuth = null;

        File file = null;
        try {

            file = ImageUtil.downLoadFromUrl(imageUrl,
                OrderNoGenerater.generate("Image") + ".jpg", IMAGE_FILE_DIR);
            RequestBody fileBody = RequestBody
                .create(MediaType.parse("image/png"), file);

            RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), fileBody)
                .addFormDataPart("trace_id", OrderNoGenerater.generate("ZQZN"))
                .addFormDataPart("id_no", idNo)
                .addFormDataPart("name", realName).build();

            String json = OkHttpUtils.doMultipartPost(
                getZqznRequestUrl(URL_IREAL_AUTH), requestBody);

            JSONObject jsonObject = JSONObject.parseObject(json);

            if (jsonObject.getBoolean("success")) {
                zqznInfoRealAuth = JSONObject.parseObject(
                    jsonObject.getJSONObject("data").toJSONString(),
                    ZqznInfoRealAuth.class);
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "实人认证失败，请联系服务商！");
            }

        } catch (IOException e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "实人认证发送异常，" + e.getMessage());
        }

        return zqznInfoRealAuth;

    }

    private static String getDomainUrl(String imageName) {
        return QiniuUtil.parseUrl(imageName);
    }

    private static String getZqznRequestUrl(String url) {
        return url.replace("{app_key}", ZQZN_APP_KEY);
    }

}
