package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UsersDynamicSqlSupport {

    public static final Users users = new Users();


    public static final SqlColumn<Long> id = users.id;


    public static final SqlColumn<String> username = users.username;


    public static final SqlColumn<String> password = users.password;


    public static final SqlColumn<String> email = users.email;


    public static final SqlColumn<String> phone = users.phone;


    public static final SqlColumn<String> status = users.status;


    public static final SqlColumn<String> remark = users.remark;


    public static final class Users extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public Users() {
            super("users");
        }
    }
}