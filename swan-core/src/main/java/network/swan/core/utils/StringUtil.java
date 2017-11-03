package network.swan.core.utils;

import com.google.common.base.CaseFormat;
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
     * <li>&quot;USER_NAME&quot; is translated to &quot;u_s_e_r__n_a_m_e&quot;</li>
     * <li>&quot;user_name&quot; is translated to &quot;user_name&quot; (unchanged)</li>
     * <li>&quot;user&quot; is translated to &quot;user&quot; (unchanged)</li>
     * <li>&quot;User&quot; is translated to &quot;user&quot;</li>
     * <li>&quot;USER&quot; is translated to &quot;u_s_e_r&quot;</li>
     * <li>&quot;_user&quot; is translated to &quot;_user&quot;</li>
     * <li>&quot;_User&quot; is translated to &quot;__user&quot;</li>
     * <li>&quot;__user&quot; is translated to &quot;__user&quot;</li>
     * <li>&quot;user__name&quot; is translated to &quot;user__name&quot; </li>
     * </ul>
     */
    public static String lowerCaseWithUnderscores(String input) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, input);
    }

    /**
     * 首字母小写
     *
     * @param input
     * @return
     */
    public static String lowerFirstCharacter(String input) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, input);
    }

    /**
     * 小写驼峰
     *
     * @param input
     * @return
     */
    public static String lowerCamel(String input) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, input);
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

}
