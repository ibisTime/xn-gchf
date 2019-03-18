// package com.cdkj.gchf.common;
//
// import java.security.Key;
// import java.security.Security;
// import java.util.Arrays;
//
// import javax.crypto.Cipher;
// import javax.crypto.spec.IvParameterSpec;
// import javax.crypto.spec.SecretKeySpec;
//
// import org.bouncycastle.jce.provider.BouncyCastleProvider;
// import org.bouncycastle.util.encoders.Base64;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// public class AesUtil2 {
//
// private static final Logger logger = LoggerFactory
// .getLogger(AesUtil2.class);
//
// /**
// * 加密
// * 模式:AES/CBC/PKCS7Padding
// * 
// * @param encodeRules 秘钥
// * @param content 加密串
// * @return
// */
// public static String AESEncode(String encodeRules, String content,
// String ivParameter) {
//
// try {
// int base = 16;
// byte[] keybyte = encodeRules.getBytes("UTF-8");
// if (keybyte.length % base != 0) {
// int groups = keybyte.length / base
// + (keybyte.length % base != 0 ? 1 : 0);
// byte[] temp = new byte[groups * base];
// Arrays.fill(temp, (byte) 0);
// System.arraycopy(keybyte, 0, temp, 0, keybyte.length);
// keybyte = temp;
// }
// // 初始化  
// Security.addProvider(new BouncyCastleProvider());
// // 转化成JAVA的密钥格式  
// Key key = new SecretKeySpec(keybyte, "AES");
// // 初始化cipher  
// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
// cipher.init(Cipher.ENCRYPT_MODE, key,
// new IvParameterSpec(ivParameter.getBytes()));
// byte[] encryptedText = cipher.doFinal(content.getBytes());
// return new String(new Base64().encode(encryptedText))
// .replaceAll("\r\n", "");
// } catch (Exception e) {
// logger.info("AESEncode error", e);
// }
// return null;
// }
//
// /**
// * 解密
// * 
// * @param encodeRules 秘钥
// * @param content 解密串
// * @return
// */
// public static String AESDecode(String encodeRules, String content,
// String ivParameter) throws Exception {
// try {
// // 判断Key是否正确  
// if (encodeRules == null) {
// logger.info("Key为空null");
// return null;
// }
// // 判断Key是否为16位  
// if (encodeRules.length() != 16) {
// logger.info("Key长度不是16位");
// return null;
// }
// byte[] raw = encodeRules.getBytes("UTF-8");
// Security.addProvider(
// new org.bouncycastle.jce.provider.BouncyCastleProvider());
// SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
// IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
// cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
// try {
// byte[] encrypted1 = new Base64().decode(content);// 先用base64解密  
// byte[] original = cipher.doFinal(encrypted1);
// String originalString = new String(original);
// return originalString;
// } catch (Exception e) {
// logger.info(e.toString());
// return null;
// }
// } catch (Exception ex) {
// logger.info(ex.toString());
// return null;
// }
// }
//
// public static void main(String args[]) {
// String src = "421081199510142315";
// String iv = "24484b262dd63dd5";
// String key = "24484b262dd63dd584902a266bdbdca0";
//
// System.out.println(AESEncode(src, key, iv));
// }
// }
