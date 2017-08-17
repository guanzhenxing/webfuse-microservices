package network.swan.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private StringUtil() {
    }

    public static final String EMPTY = "";

    public static final String COMMA = ",";

    /**
     * These rules result in the following additional example translations from
     * Java property names to JSON element names.
     * <ul><li>&quot;userName&quot; is translated to &quot;user_name&quot;</li>
     * <li>&quot;UserName&quot; is translated to &quot;user_name&quot;</li>
     * <li>&quot;USER_NAME&quot; is translated to &quot;user_name&quot;</li>
     * <li>&quot;user_name&quot; is translated to &quot;user_name&quot; (unchanged)</li>
     * <li>&quot;user&quot; is translated to &quot;user&quot; (unchanged)</li>
     * <li>&quot;User&quot; is translated to &quot;user&quot;</li>
     * <li>&quot;USER&quot; is translated to &quot;user&quot;</li>
     * <li>&quot;_user&quot; is translated to &quot;user&quot;</li>
     * <li>&quot;_User&quot; is translated to &quot;user&quot;</li>
     * <li>&quot;__user&quot; is translated to &quot;_user&quot;
     * (the first of two underscores was removed)</li>
     * <li>&quot;user__name&quot; is translated to &quot;user__name&quot;
     * (unchanged, with two underscores)</li></ul>
     */
    public static String lowerCaseWithUnderscores(String input) {
        if (input == null) return input; // garbage in, garbage out
        int length = input.length();
        StringBuilder result = new StringBuilder(length * 2);
        int resultLength = 0;
        boolean wasPrevTranslated = false;
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (i > 0 || c != '_') { // skip first starting underscore
                if (Character.isUpperCase(c)) {
                    if (!wasPrevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != '_') {
                        result.append('_');
                        resultLength++;
                    }
                    c = Character.toLowerCase(c);
                    wasPrevTranslated = true;
                } else {
                    wasPrevTranslated = false;
                }
                result.append(c);
                resultLength++;
            }
        }
        return resultLength > 0 ? result.toString() : input;
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerFirstCharacter(String str) {
        if (StringUtils.isEmpty(str)) return str;
        char[] arrays = str.toCharArray();
        char firstUpperCaseChar = arrays[0];
        char firstLowercaseChar = Character.toLowerCase(firstUpperCaseChar);
        arrays[0] = firstLowercaseChar;
        return new String(arrays);
    }

    /**
     * 过滤字符串的HTML标签
     *
     * @param str 含html标签的字符串
     * @return htmlStr
     */
    public static String removeHtmlText(String str) {
        if (null == str) {
            return str;
        }
        String htmlStr = str;
        String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";// 定义script的正则表达式
        String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";// 定义style的正则表达式
        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式
        Pattern scriptPattern = null;
        Matcher scriptMatcher = null;
        Pattern stylePattern = null;
        Matcher styleMatcher = null;
        Pattern htmlPattern = null;
        Matcher htmlMatcher = null;
        try {
            scriptPattern = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            scriptMatcher = scriptPattern.matcher(htmlStr);
            htmlStr = scriptMatcher.replaceAll("");  // 过滤script标签
            stylePattern = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE); // 过滤style标签
            styleMatcher = stylePattern.matcher(htmlStr);
            htmlStr = styleMatcher.replaceAll("");
            htmlPattern = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);// 过滤html标签
            htmlMatcher = htmlPattern.matcher(htmlStr);
            htmlStr = htmlMatcher.replaceAll("");
            htmlStr = htmlStr.replaceAll("&nbsp;", "");
        } catch (Exception e) {
            throw new RuntimeException("error occur when filter html content:" + e.getMessage(), e);
        }
        return htmlStr;
    }


    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     *
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given {@code String} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code String} is not {@code null}, its length is greater than 0,
     * and it contains at least one non-whitespace character.
     *
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null}, its
     * length is greater than 0, and it does not contain whitespace only
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * Check whether the given {@code CharSequence} contains any whitespace characters.
     *
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not empty and
     * contains at least 1 whitespace character
     * @see Character#isWhitespace
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given {@code String} contains any whitespace characters.
     *
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not empty and
     * contains at least 1 whitespace character
     * @see #containsWhitespace(CharSequence)
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    /**
     * Check that the given {@code CharSequence} is neither {@code null} nor
     * of length 0.
     * <p>Note: this method returns {@code true} for a {@code CharSequence}
     * that purely consists of whitespace.
     * <p><pre class="code">
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     *
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given {@code String} is neither {@code null} nor of length 0.
     * <p>Note: this method returns {@code true} for a {@code String} that
     * purely consists of whitespace.
     *
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null} and has length
     * @see #hasLength(CharSequence)
     * @see #hasText(String)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

}
