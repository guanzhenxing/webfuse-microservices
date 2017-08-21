package network.swan.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class RegexUtil {

    public static boolean matches(String regex, String input) {
        return matcher(regex, input).matches();
    }

    public static boolean lookingAt(String regex, String input) {
        return matcher(regex, input).lookingAt();
    }

    public static boolean find(String regex, String input) {
        return matcher(regex, input).find();
    }

    public static Matcher matcher(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
