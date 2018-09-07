package cn.webfuse.common.kit.id;

import java.util.UUID;

public class IdGenerator {


//    private static class SingleSnowflake {
//        private SnowflakeKits kits;
//
//        private static class SingletonHolder {
//            private static final SingleSnowflake INSTANCE = new SingleSnowflake();
//        }
//
//        private SingleSnowflake() {
//            kits = new SnowflakeKits(0, 0);
//        }
//
//        public static final SingleSnowflake getInstance() {
//            return SingletonHolder.INSTANCE;
//        }
//
//        public SnowflakeKits getKits() {
//            return kits;
//        }
//    }
//
//
//
//
//
//    public static String buildId(String type) {
//        if ("SNOW_FLAKE".equalsIgnoreCase(type)) {
//            long id = SingleSnowflake.getInstance().getKits().nextId();
//            return String.valueOf(id);
//        } else if ("OBJECT_ID".equalsIgnoreCase(type)) {
//            return ObjectIdKits.next();
//        }
//        return UUID.randomUUID().toString();
//    }


}
