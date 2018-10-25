package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ResourcesDynamicSqlSupport {

    public static final Resources resources = new Resources();


    public static final SqlColumn<Long> id = resources.id;


    public static final SqlColumn<String> name = resources.name;


    public static final SqlColumn<String> type = resources.type;


    public static final SqlColumn<String> code = resources.code;


    public static final SqlColumn<String> remark = resources.remark;


    public static final SqlColumn<String> status = resources.status;


    public static final class Resources extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public Resources() {
            super("resources");
        }
    }
}