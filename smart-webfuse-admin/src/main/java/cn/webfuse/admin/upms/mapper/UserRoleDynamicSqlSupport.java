package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserRoleDynamicSqlSupport {

    public static final UserRole userRole = new UserRole();


    public static final SqlColumn<Long> id = userRole.id;


    public static final SqlColumn<Long> userId = userRole.userId;


    public static final SqlColumn<Long> roleId = userRole.roleId;


    public static final class UserRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public UserRole() {
            super("user_role");
        }
    }
}