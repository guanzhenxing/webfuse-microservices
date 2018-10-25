package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserGroupDynamicSqlSupport {

    public static final UserGroup userGroup = new UserGroup();


    public static final SqlColumn<Long> id = userGroup.id;


    public static final SqlColumn<Long> userId = userGroup.userId;


    public static final SqlColumn<Long> groupId = userGroup.groupId;


    public static final class UserGroup extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> groupId = column("group_id", JDBCType.BIGINT);

        public UserGroup() {
            super("user_group");
        }
    }
}