package cn.webfuse.common.kit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Locale;

/**
 * 数字的工具类
 * <p>
 * copy from vipshop VJTools(com.vip.vjtools.vjkit.number.NumberUtil) and made some changes.
 */
public class NumberKits {

    private static final double DEFAULT_DOUBLE_EPSILON = 0.00001d;

    /**
     * 因为double的精度问题, 允许两个double在0.00001内的误差为相等。
     */
    public static boolean equalsWithin(double d1, double d2) {
        return Math.abs(d1 - d2) < DEFAULT_DOUBLE_EPSILON;
    }

    /**
     * 因为double的精度问题, 允许两个double在epsilon内的误差为相等
     */
    public static boolean equalsWithin(double d1, double d2, double epsilon) {
        return Math.abs(d1 - d2) < epsilon;
    }


    /**
     * 判断字符串是否合法数字
     */
    public static boolean isNumber(String str) {
        return NumberUtils.isCreatable(str);
    }

    /**
     * 判断字符串是否16进制
     */
    public static boolean isHexNumber(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }

        int index = value.startsWith("-") ? 1 : 0;
        return value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index);
    }

    /**
     * 将10进制的String安全的转化为Integer.
     * <p>
     * 当str为空或非数字字符串时，返回default值
     */
    public static Integer toIntObject(String str, Integer defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将10进制的String安全的转化为Float.
     * <p>
     * 当str为空或非数字字符串时，返回default值
     */
    public static Float toFloatObject(String str, Float defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Float.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }


    /**
     * 将10进制的String安全的转化为Long.
     * <p>
     * 当str为空或非数字字符串时，返回default值
     */
    public static Long toLongObject(String str, Long defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }


    /**
     * 将10进制的String安全的转化为Long.
     * <p>
     * 当str为空或非数字字符串时，返回default值
     */
    public static Double toDoubleObject(String str, Double defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }


    /**
     * 将16进制的String转化为Integer，出错时返回默认值.
     */
    public static Integer hexToIntObject(String str, Integer defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Integer.decode(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }


    /**
     * 将16进制的String转化为Long，出错时返回默认值.
     */
    public static Long hexToLongObject(String str, Long defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Long.decode(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /////// toString ///////
    // 定义了原子类型与对象类型的参数，保证不会用错函数会导致额外AutoBoxing转换//

    public static String toString(int i) {
        return Integer.toString(i);
    }

    public static String toString(Integer i) {
        return i.toString();
    }

    public static String toString(float f) {
        return Float.toString(f);
    }

    public static String toString(Float f) {
        return Float.toString(f);
    }

    public static String toString(long l) {
        return Long.toString(l);
    }

    public static String toString(Long l) {
        return l.toString();
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    public static String toString(Double d) {
        return d.toString();
    }

    /**
     * 输出格式化为小数后两位的double字符串
     */
    public static String to2DigitString(double d) {
        return String.format(Locale.ROOT, "%.2f", d);
    }

    /////////// 杂项 ///////

    /**
     * 安全的将小于Integer.MAX的long转为int，否则抛出IllegalArgumentException异常
     */
    public static int toInt32(long x) {
        if ((int) x == x) {
            return (int) x;
        }
        throw new IllegalArgumentException("Int " + x + " out of range");
    }
}
