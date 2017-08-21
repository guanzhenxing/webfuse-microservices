package network.swan.core.lang.constant;

public enum Regex {

    /**
     * 数字
     */
    DIGIT("^[0-9]*$"),
    /**
     * 汉字
     */
    CHINESE_CHARACTERS("^[\\u4e00-\\u9fa5]{0,}$"),
    /**
     * 字母和数字
     */
    ALPHABET_AND_DIGIT("^[A-Za-z0-9]+$"),
    /**
     * 26个字母
     */
    ALPHABET("^[A-Za-z]+$"),

    /**
     * email
     */
    EMAIL("^\\w+([-+.]\\w+)@\\w+([-.]\\w+).\\w+([-.]\\w+)*$"),

    /**
     * 域名
     */
    DOMAIN_NAME("[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?"),

    /**
     * INTERNET_URL
     */
    INTERNET_URL("[a-zA-z]+://[^\\s] 或 ^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=])?$"),

    /**
     * 电话号码（固话）
     */
    TELEPHONE("^(\\d{3,4}-)?\\d{6,8}(-\\d{1,6})?$"),

    /**
     * 包含区号的固话号码
     */
    TELEPHONE_WITH_AREACODE("^\\d{3,4}-\\d{6,8}(-\\d{1,6})?$"),

    /**
     * 手机号码
     */
    MOBILEPHONE("^((13[0-9])|(14[5|7])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$"),

    /**
     * 中国邮政编码
     */
    ZIPCODE("^\\d{6}$");

    private String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return this.regex;
    }

}
