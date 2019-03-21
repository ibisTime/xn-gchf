package com.cdkj.gchf.common;

import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Created by jim.z.hu on 2019/3/20.
 */
public final class AesUtils {
    private static final String CHARSET_NAME = "UTF-8";

    private static final String AES_NAME = "AES";

    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * ¼ÓÃÜ
     *
     * @param content
     * @param key
     * @return
     */
    public static String encrypt(@NotNull String content, @NotNull String key) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(
                key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(
                subBytes(key.getBytes(CHARSET_NAME)));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes(CHARSET_NAME));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Base64.encodeBase64String(result);
    }

    /**
     * ½âÃÜ
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(@NotNull String content, @NotNull String key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(
                key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(
                subBytes(key.getBytes(CHARSET_NAME)));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)),
                CHARSET_NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    /**
     * ´ÓÒ»¸öbyte[]Êý×éÖÐ½ØÈ¡Ò»²¿·Ö
     * @param src
     * @return
     */
    public static byte[] subBytes(byte[] src) {
        if (src.length < 16) {
            throw new RuntimeException("ÎÞ·¨´ÓKeyÖÐ»ñÈ¡Æ«ÒÆÁ¿!");
        }
        byte[] bs = new byte[16];
        for (int i = 0; i < 16; i++) {
            bs[i] = src[i];
        }
        return bs;
    }
}
