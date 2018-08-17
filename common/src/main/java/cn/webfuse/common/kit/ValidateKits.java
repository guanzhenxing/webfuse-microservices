package cn.webfuse.common.kit;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 */
public class ValidateKits {

    /**
     * 校验，是否匹配
     */
    public static boolean isMatch(final Pattern pattern, final CharSequence input) {
        return StringUtils.isNotEmpty(input) && pattern.matcher(input).matches();
    }

    /**
     * 校验，是否匹配
     */
    public static boolean isMatch(final String regexStr, final CharSequence input) {
        Pattern pattern = Pattern.compile(regexStr);
        return isMatch(pattern, input);
    }

    /**
     * 返回与正则表达式匹配的列表
     */
    public static List<String> getMatches(final String regex, final CharSequence input) {
        if (input == null) return Collections.emptyList();
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }


}
