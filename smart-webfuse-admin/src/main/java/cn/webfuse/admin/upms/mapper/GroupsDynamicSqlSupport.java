package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class GroupsDynamicSqlSupport {

    public static final Groups groups = new Groups();


    public static final SqlColumn<Long> id = groups.id;


    public static final SqlColumn<String> name = groups.name;


    public static final SqlColumn<String> remark = groups.remark;


    public static final class Groups extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public Groups() {
            super("groups");
        }
    }
}