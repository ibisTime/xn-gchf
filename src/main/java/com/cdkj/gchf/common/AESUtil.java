package com.cdkj.gchf.common;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;

import com.cdkj.gchf.exception.BizException;

public class AESUtil {

    // 加密
    public static String jdkAESEncryption(String src) {
        String result = null;
        try {
            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed("bcoin".getBytes());
            keyGenerator.init(128, random);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(src.getBytes());
            result = Hex.toHexString(encodeResult);
        } catch (Exception e) {
            throw new BizException("AES加密发生异常，原因：" + e.getMessage());
        }
        return result;
    }

    // 解密
    public static String jdkAESDecryption(String src) {
        String result = null;
        try {

            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed("bcoin".getBytes());
            keyGenerator.init(128, random);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(Hex.decode(src));
            result = new String(decodeResult);

        } catch (Exception e) {
            throw new BizException("AES解密发生异常，原因：" + e.getMessage());
        }
        return result;

    }
    //
    // public static String govAESEncryption(String src, String sKey) {
    //
    // String result = null;
    // try {
    // byte[] raw = sKey.getBytes("utf-8");
    // SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
    // Cipher cipher;
    // cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");// "算法/模式/补码方式"
    // cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    // byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
    //
    // result = new Base64().encodeToString(encrypted);//
    // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    //
    // return result;
    //
    // }

    public static String encrypt(String content, String key) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"),
                "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(
                new byte[16]);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Base64.encodeBase64String(result);
    }

    public static void main(String[] args) {
        String secretString = "381874747048";
        System.out.println(secretString);
        System.out
            .println(encrypt(secretString, "8ec1924ad80f349e71fadf50e75db627"));
    }
}
