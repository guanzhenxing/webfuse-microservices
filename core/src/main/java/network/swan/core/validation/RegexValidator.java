package network.swan.core.validation;

import network.swan.core.utils.RegexUtil;

/**
 * 正则表达式验证
 */
public class RegexValidator {

    /**
     * 电话号码（固话）
     */
    public static final String TELEPHONE = "^(\\d{3,4}-)?\\d{6,8}(-\\d{1,6})?$";

    /**
     * 包含区号的固话号码
     */
    public static final String TELEPHONE_MUST_AREACODE = "^\\d{3,4}-\\d{6,8}(-\\d{1,6})?$";

    /**
     * 手机号码
     */
    public static final String MOBILEPHONE = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$";

    /**
     * 邮政编码
     */
    public static final String ZIPCODE = "^\\d{6}$";


    /**
     * 校验
     *
     * @param value 值
     * @param regex 正则
     * @return 是否匹配
     */
    public static boolean validator(String value, String regex) {
        return RegexUtil.matcher(regex, value).matches();
    }
}
