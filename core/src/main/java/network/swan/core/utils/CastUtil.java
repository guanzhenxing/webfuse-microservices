package network.swan.core.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 转型工具
 */
public class CastUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private CastUtil() {
    }

    /**
     * 将Object类型转成boolean；如果发生错误默认为false
     *
     * @param object 待转数据
     * @return
     */
    public static boolean object2boolean(Object object) {
        return object2boolean(object, false);
    }

    /**
     * 将Object类型转成boolean；如果发生错误，默认值为defaultValue
     *
     * @param object       待转数据
     * @param defaultValue 默认值
     * @return
     */
    public static boolean object2boolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(object2String(object));
        }
        return booleanValue;
    }

    /**
     * 将object数组转成boolean数组
     *
     * @param objArray 待转数据
     * @return
     */
    public static boolean[] object2booleanArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        boolean[] booleanArray = new boolean[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                booleanArray[i] = object2boolean(objArray[i]);
            }
        }
        return booleanArray;
    }

    /**
     * 将object对象转成double值，如果发生错误默认值为0
     *
     * @param object
     * @return
     */
    public static double object2double(Object object) {
        return object2double(object, 0d);
    }

    /**
     * 将object对象转成double；如果发生错误，默认值为defaultValue
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static double object2double(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String strValue = object2String(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                    LOGGER.error("object2double error:", e);
                }
            }
        }
        return doubleValue;
    }

    /**
     * 将object数组转成double数组
     *
     * @param objArray
     * @return
     */
    public static double[] object2doubleArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        double[] doubleArray = new double[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                doubleArray[i] = object2double(objArray[i]);
            }
        }
        return doubleArray;
    }

    /**
     * 将object对象转成int值
     *
     * @param object
     * @return
     */
    public static int object2int(Object object) {
        return object2int(object, 0);
    }

    /**
     * 将object对象转成int；如果发生错误，默认值为defaultValue
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static int object2int(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String strValue = object2String(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                    LOGGER.error("object2int error:", e);
                }
            }
        }
        return intValue;
    }

    /**
     * 将object数组转成int数组
     *
     * @param objArray
     * @return
     */
    public static int[] object2intArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int[] intArray = new int[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                intArray[i] = object2int(objArray[i]);
            }
        }
        return intArray;
    }

    /**
     * 将object对象转换成float值
     *
     * @param object
     * @return
     */
    public static float object2float(Object object) {
        return object2float(object, 0f);
    }

    /**
     * 将object对象转成float；如果发生错误，默认值为defaultValue
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static float object2float(Object object, float defaultValue) {
        float floatValue = defaultValue;
        if (object != null) {
            String strValue = object2String(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    floatValue = Float.parseFloat(strValue);
                } catch (NumberFormatException e) {
                    floatValue = defaultValue;
                    LOGGER.error("object2float error:", e);
                }
            }
        }
        return floatValue;
    }

    /**
     * 将object数组转成float数组
     *
     * @param objArray
     * @return
     */
    public static float[] object2floatArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        float[] floatArray = new float[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                floatArray[i] = object2float(objArray[i]);
            }
        }
        return floatArray;
    }

    /**
     * 将object对象转成long值
     *
     * @param object
     * @return
     */
    public static long object2long(Object object) {
        return object2long(object, 0L);
    }

    /**
     * 将object对象转成long；如果发生错误，默认值为defaultValue
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static long object2long(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (object != null) {
            String strValue = object2String(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                    LOGGER.error("object2long error:", e);
                }
            }
        }
        return longValue;
    }

    /**
     * 将object数组转成long数组
     *
     * @param objArray
     * @return
     */
    public static long[] object2longArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        long[] longArray = new long[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                longArray[i] = object2long(objArray[i]);
            }
        }
        return longArray;
    }

    /**
     * 将object对象转成String
     *
     * @param object
     * @return
     */
    public static String object2String(Object object) {
        return object2String(object, "");
    }

    /**
     * 将object对象转成string；如果发生错误，默认值为defaultValue
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static String object2String(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 将object数组转成String数组
     *
     * @param objArray
     * @return
     */
    public static String[] object2StringArray(Object[] objArray) {

        if (objArray == null) {
            objArray = new Object[0];
        }
        String[] strArray = new String[objArray.length];

        if (ArrayUtils.isNotEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                strArray[i] = object2String(objArray[i]);
            }
        }
        return strArray;
    }
}
