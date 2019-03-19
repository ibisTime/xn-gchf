package com.cdkj.gchf.common;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AesUtils {
    public static void main(String[] args) throws Exception {

        // 原始数据
        String data = "413024196804304833";
        // 密钥
        String sessionKey = "24484b262dd63dd584902a266bdbdca0";
        // 向量
        String iv = "24484b262dd63dd5";

        // 用Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        String baseData = encoder.encodeToString(data.getBytes());
        String baseSessionKey = encoder.encodeToString(sessionKey.getBytes());
        String baseIv = encoder.encodeToString(iv.getBytes());

        // 导入支持AES/CBC/PKCS7Padding的Provider
        Security.addProvider(new BouncyCastleProvider());

        // 获取加密数据
        String encryptedData = encrypt(baseData, baseSessionKey, baseIv);

        System.out.println(encryptedData);
    }

    public static String encrypt(String data, String sessionKey, String iv)
            throws Exception {

        // 加密之前，先从Base64格式还原到原始格式
        Decoder decoder = Base64.getDecoder();
        byte[] dataByte = decoder.decode(data);
        byte[] keyByte = decoder.decode(sessionKey);
        byte[] ivByte = decoder.decode(iv);

        String encryptedData = null;

        // 指定算法，模式，填充方式，创建一个Cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");

        // 生成Key对象
        Key sKeySpec = new SecretKeySpec(keyByte, "AES");

        // 把向量初始化到算法参数
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(ivByte));

        // 指定用途，密钥，参数 初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, params);

        // 指定加密
        byte[] result = cipher.doFinal(dataByte);

        // 对结果进行Base64编码，否则会得到一串乱码，不便于后续操作
        Base64.Encoder encoder = Base64.getEncoder();
        encryptedData = encoder.encodeToString(result);

        return encryptedData;
    }
}
