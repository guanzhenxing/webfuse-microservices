package cn.webfuse.framework.core.kit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 加解密工具类
 */
public class CryptoKits {

    /**
     * 32位的MD5加密
     */
    public String md5(String input) {
        return DigestUtils.md5Hex(input);
    }

    /**
     * SHA1加密
     */
    public String sha1(String input) {
        return DigestUtils.sha1Hex(input);
    }

    /**
     * SHA256加密
     */
    public String sha256(String input) {
        return DigestUtils.sha256Hex(input);
    }

    /**
     * SHA384加密
     */
    public String sha384(String input) {
        return DigestUtils.sha384Hex(input);
    }

    /**
     * SHA512加密
     */
    public String sha512(String input) {
        return DigestUtils.sha512Hex(input);
    }

    /**
     * HmacMD5加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return Hex编码后的加密结果
     */
    public String hmacMD5(String input, String key) {
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_MD5, key).hmac(input);
        return EncodeKits.encodeHex(hmac);
    }

    /**
     * HmacSHA1加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return Hex编码后的加密结果
     */
    public String hmacSHA1(String input, String key) {
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, key).hmac(input);
        return EncodeKits.encodeHex(hmac);
    }

    /**
     * HmacSHA224加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return Hex编码后的加密结果
     */
    public static String hmacSHA224(String input, String key) {
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_224, key).hmac(input);
        return EncodeKits.encodeHex(hmac);
    }

    /**
     * HmacSHA256加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return Hex编码后的加密结果
     */
    public static String hmac256(String input, String key) {
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmac(input);
        return EncodeKits.encodeHex(hmac);
    }

    /**
     * HmacSHA384加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return Hex编码后的加密结果
     */
    public static String hmac384(String input, String key) {
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_384, key).hmac(input);
        return EncodeKits.encodeHex(hmac);
    }


    /**
     * HmacSHA512加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return Hex编码后的加密结果
     */
    public static String hmac512(String input, String key) {
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, key).hmac(input);
        return EncodeKits.encodeHex(hmac);
    }

    /**
     * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节). HMAC-SHA1算法对密钥无特殊要求, RFC2401建议最少长度为160位(20字节).
     */
    public static byte[] generateHmacSha1Key() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(HmacAlgorithms.HMAC_SHA_1.toString());
            keyGenerator.init(160);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            throw ExceptionKits.unchecked(e);
        }
    }


    /**
     * DES加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return DES加密后的结果
     */
    public static String encryptDES(String input, String key) {
        try {
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherData = cipher.doFinal(input.getBytes());
            return EncodeKits.encodeBase64(cipherData);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException |
                NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw ExceptionKits.unchecked(e);
        }
    }

    /**
     * DES解密
     *
     * @param input 需要解密的数据
     * @param key   秘钥
     * @return DES解密后的结果
     */
    public static String decryptDES(String input, String key) {
        try {
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] cipherData = cipher.doFinal(input.getBytes());
            return new String(cipherData);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException |
                NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw ExceptionKits.unchecked(e);
        }
    }


    /**
     * AES加密
     *
     * @param input 需要加密的数据
     * @param key   秘钥
     * @return AES加密后的结果
     */
    public static String encryptAES(String input, String key) {

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
            byte[] bytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return EncodeKits.encodeBase64(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw ExceptionKits.unchecked(e);
        }

    }

    /**
     * AES解密
     *
     * @param input 需要解密的数据
     * @param key   秘钥
     * @return AES解密后的结果
     */
    public static String decryptAES(String input, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
            byte[] bytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return new String(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw ExceptionKits.unchecked(e);
        }

    }

    /**
     * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
     */
    public static byte[] generateAesKey() {
        return generateAesKey(128);
    }

    /**
     * 生成AES密钥,可选长度为128,192,256位.
     */
    public static byte[] generateAesKey(int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(keysize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            throw ExceptionKits.unchecked(e);
        }
    }


}
