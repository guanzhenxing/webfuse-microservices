package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PermissionsDynamicSqlSupport {

    public static final Permissions permissions = new Permissions();


    public static final SqlColumn<Long> id = permissions.id;


    public static final SqlColumn<String> name = permissions.name;


    public static final SqlColumn<String> code = permissions.code;


    public static final SqlColumn<String> remark = permissions.remark;


    public static final SqlColumn<String> status = permissions.status;


    public static final SqlColumn<String> type = permissions.type;


    public static final class Permissions extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public Permissions() {
            super("permissions");
        }
    }
}