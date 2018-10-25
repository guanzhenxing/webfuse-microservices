package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RolePermissionDynamicSqlSupport {

    public static final RolePermission rolePermission = new RolePermission();


    public static final SqlColumn<Long> id = rolePermission.id;


    public static final SqlColumn<Long> roleId = rolePermission.roleId;


    public static final SqlColumn<Long> permissionId = rolePermission.permissionId;


    public static final class RolePermission extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> permissionId = column("permission_id", JDBCType.BIGINT);

        public RolePermission() {
            super("role_permission");
        }
    }
}