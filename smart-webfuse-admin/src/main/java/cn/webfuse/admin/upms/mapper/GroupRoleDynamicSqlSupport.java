package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class GroupRoleDynamicSqlSupport {

    public static final GroupRole groupRole = new GroupRole();


    public static final SqlColumn<Long> id = groupRole.id;


    public static final SqlColumn<Long> groupId = groupRole.groupId;


    public static final SqlColumn<Long> roleId = groupRole.roleId;


    public static final class GroupRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> groupId = column("group_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public GroupRole() {
            super("group_role");
        }
    }
}