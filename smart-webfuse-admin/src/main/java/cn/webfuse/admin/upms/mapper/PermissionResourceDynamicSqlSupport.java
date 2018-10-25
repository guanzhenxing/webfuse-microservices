package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PermissionResourceDynamicSqlSupport {

    public static final PermissionResource permissionResource = new PermissionResource();


    public static final SqlColumn<Long> id = permissionResource.id;


    public static final SqlColumn<Long> permissionId = permissionResource.permissionId;


    public static final SqlColumn<Long> resourceId = permissionResource.resourceId;


    public static final class PermissionResource extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> permissionId = column("permission_id", JDBCType.BIGINT);

        public final SqlColumn<Long> resourceId = column("resource_id", JDBCType.BIGINT);

        public PermissionResource() {
            super("permission_resource");
        }
    }
}