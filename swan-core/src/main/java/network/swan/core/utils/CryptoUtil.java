package network.swan.core.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;

/**
 * 加解密工具类。实现：<br/>
 * <li>DES加密解密</li>
 * <li>MD5加密</li>
 * <li>SHA-1加密</li>
 *
 */
public class CryptoUtil {

    private CryptoUtil() {
    }

    /**
     * MD5加密
     *
     * @param data 要进行加密的字符串
     * @return MD5加密后的字符串
     */
    public static String md5Encrypt(String data) {
        return encrypt("MD5", data);
    }

    /**
     * SHA-1加密
     *
     * @param data 要进行加密的字符串
     * @return SHA-1加密后的字符串
     */
    public static String sha1Encrypt(String data) {
        return encrypt("SHA-1", data);
    }

    /**
     * DES加密后用Base64进行编码
     *
     * @param data 要进行加密的数据
     * @param key  密钥
     * @param ivs  偏移量
     * @return DES加密过后用Base64进行编码后的数据
     */
    @SuppressWarnings("restriction")
    public static String desEncrypt_encodeBase64(String data, String key, String ivs) {

        String retData = "";

        try {
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

            byte[] bt = cipher.doFinal(data.getBytes());
            retData = new sun.misc.BASE64Encoder().encode(bt);

        } catch (Exception e) {
            ExceptionUtil.unchecked(e);
        }

        return retData;
    }

    /**
     * DES加密
     *
     * @param data 要进行加密的数据
     * @param key  密钥
     * @param ivs  偏移量
     * @return DES加密过后的数据
     */
    public static byte[] desEncrypt(String data, String key, String ivs) {

        byte[] retResult = null;

        try {
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

            retResult = cipher.doFinal(data.getBytes());

        } catch (Exception e) {
            ExceptionUtil.unchecked(e);
        }
        return retResult;
    }

    /**
     * 对Base64编码后的加密字符串进行DES解密
     *
     * @param data 加密数据（Base64编码后的加密数据）
     * @param key  密钥
     * @param ivs  偏移量
     * @return 解密后的数据
     */
    @SuppressWarnings("restriction")
    public static String desDecrypt_decodeBase64(String data, String key, String ivs) {

        String retData = "";
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);

            byte[] bt = cipher.doFinal(buf);

            retData = new String(bt);

        } catch (Exception e) {
            ExceptionUtil.unchecked(e);
        }
        return retData;
    }

    /**
     * DES解密
     *
     * @param data 加密数据
     * @param key  密钥
     * @param ivs  偏移量
     * @return 解密后的数据
     */
    public static byte[] desDecrypt(String data, String key, String ivs) {

        byte[] retResult = null;

        try {
            byte[] buf = data.getBytes();

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);

            retResult = cipher.doFinal(buf);

        } catch (Exception e) {
            ExceptionUtil.unchecked(e);
        }

        return retResult;
    }

    private static String encrypt(String algorithm, String data) {
        try {
            StringBuilder result = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(data.getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1)
                    result.append("0");
                result.append(hex);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
