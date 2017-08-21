package network.swan.core.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 各种编码工具类。实现：
 * <li>HEX编码</li>
 * <li>BASE64编码</li>
 * <li>URL编码</li>
 * <li>HTML转码</li>
 * <li>XML转码</li>
 */
public class EncodeUtil {
    private EncodeUtil() {
    }

    /**
     * Hex编码
     *
     * @param data
     * @return
     */
    public static String encodeHex(byte[] data) {
        return Hex.encodeHexString(data);
    }

    /**
     * Hex解码
     *
     * @param data
     * @return
     */
    public static byte[] decodeHex(String data) {
        try {
            return Hex.decodeHex(data.toCharArray());
        } catch (DecoderException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * Base64编码
     *
     * @param data
     * @return
     */
    public static String encodeBase64(byte[] data) {
        return Base64.encodeBase64String(data);
    }

    /**
     * Base64解码
     *
     * @param data
     * @return
     */
    public static byte[] decodeBase64(String data) {
        return Base64.decodeBase64(data);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Html 转码.
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * Xml 解码.
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     */
    public static String encodeUrl(String data) {
        return new String(URLCodec.encodeUrl(null, data.getBytes()));
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     */
    public static String decodeUrl(String data) {

        try {
            return new String(URLCodec.decodeUrl(data.getBytes()));
        } catch (DecoderException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

}
