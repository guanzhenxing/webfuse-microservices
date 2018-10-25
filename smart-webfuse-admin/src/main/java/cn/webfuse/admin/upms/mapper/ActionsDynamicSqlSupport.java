package cn.webfuse.admin.upms.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ActionsDynamicSqlSupport {

    public static final Actions actions = new Actions();


    public static final SqlColumn<Long> id = actions.id;


    public static final SqlColumn<String> name = actions.name;


    public static final SqlColumn<String> code = actions.code;


    public static final SqlColumn<String> remark = actions.remark;


    public static final SqlColumn<String> status = actions.status;


    public static final class Actions extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public Actions() {
            super("actions");
        }
    }
}