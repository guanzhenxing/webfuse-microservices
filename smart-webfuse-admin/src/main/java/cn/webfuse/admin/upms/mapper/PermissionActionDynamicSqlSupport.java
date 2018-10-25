package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PermissionActionDynamicSqlSupport {

    public static final PermissionAction permissionAction = new PermissionAction();


    public static final SqlColumn<Long> id = permissionAction.id;


    public static final SqlColumn<Long> permissionId = permissionAction.permissionId;


    public static final SqlColumn<Long> actionId = permissionAction.actionId;


    public static final class PermissionAction extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> permissionId = column("permission_id", JDBCType.BIGINT);

        public final SqlColumn<Long> actionId = column("action_id", JDBCType.BIGINT);

        public PermissionAction() {
            super("permission_action");
        }
    }
}