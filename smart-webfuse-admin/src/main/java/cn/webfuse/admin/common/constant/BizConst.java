package cn.webfuse.admin.common.constant;

public class BizConst {

    /**
     * 通用的是否布尔值
     */
    public enum YesOrNo {

        /**
         * 否
         */
        NO(0, "否"),

        /**
         * 是
         */
        YES(1, "是");

        YesOrNo(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

}
