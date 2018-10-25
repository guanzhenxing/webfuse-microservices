package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RolesDynamicSqlSupport {

    public static final Roles roles = new Roles();


    public static final SqlColumn<Long> id = roles.id;


    public static final SqlColumn<String> code = roles.code;


    public static final SqlColumn<String> name = roles.name;


    public static final SqlColumn<String> status = roles.status;


    public static final SqlColumn<String> type = roles.type;


    public static final SqlColumn<String> remark = roles.remark;


    public static final class Roles extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public Roles() {
            super("roles");
        }
    }
}