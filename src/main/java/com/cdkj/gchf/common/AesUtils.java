package com.cdkj.gchf.common;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AesUtils {
    static {
        // 导入支持AES/CBC/PKCS7Padding的Provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {

        // 原始数据
        String data = "413024196804304833";
        // 密钥
        String sessionKey = "24484b262dd63dd584902a266bdbdca0";

        // 获取加密数据
        String encryptedData1 = encrypt(data, sessionKey);

        System.out.println(encryptedData1);
    }

    public static String encrypt(String data, String sessionKey) {
        String encryptedData = null;
        try {

            Security.addProvider(new BouncyCastleProvider());

            String iv = sessionKey.substring(0, 16);

            // 用Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String baseData = encoder.encode(data.getBytes());
            String baseSessionKey = encoder.encode(sessionKey.getBytes());
            String baseIv = encoder.encode(iv.getBytes());

            // 加密之前，先从Base64格式还原到原始格式
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] dataByte = decoder.decodeBuffer(baseData);
            byte[] keyByte = decoder.decodeBuffer(baseSessionKey);
            byte[] ivByte = decoder.decodeBuffer(baseIv);

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
            encryptedData = encoder.encode(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedData;
    }
}
