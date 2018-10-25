package cn.webfuse.data.mybatis.metadata;

import lombok.Getter;

@Getter
public enum IdentityDialect {

    DB2("VALUES IDENTITY_VAL_LOCAL()"),
    MYSQL("SELECT LAST_INSERT_ID()"),
    SQL_SERVER("SELECT SCOPE_IDENTITY()"),
    CLOUDSCAPE("VALUES IDENTITY_VAL_LOCAL()"),
    DERBY("VALUES IDENTITY_VAL_LOCAL()"),
    HSQLDB("CALL IDENTITY()"),
    SYBASE("SELECT @@IDENTITY"),
    DB2_MF("SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1"),
    INFORMIX("select dbinfo('sqlca.sqlerrd1') from systables where tabid=1");


    private String identityRetrievalStatement;

    IdentityDialect(String identityRetrievalStatement) {
        this.identityRetrievalStatement = identityRetrievalStatement;
    }

    public static IdentityDialect getIdentityDialect(String db) {
        IdentityDialect returnValue = null;
        if ("DB2".equalsIgnoreCase(db)) {
            returnValue = DB2;
        } else if ("MySQL".equalsIgnoreCase(db)) {
            returnValue = MYSQL;
        } else if ("SqlServer".equalsIgnoreCase(db)) {
            returnValue = SQL_SERVER;
        } else if ("Cloudscape".equalsIgnoreCase(db)) {
            returnValue = CLOUDSCAPE;
        } else if ("Derby".equalsIgnoreCase(db)) {
            returnValue = DERBY;
        } else if ("HSQLDB".equalsIgnoreCase(db)) {
            returnValue = HSQLDB;
        } else if ("SYBASE".equalsIgnoreCase(db)) {
            returnValue = SYBASE;
        } else if ("DB2_MF".equalsIgnoreCase(db)) {
            returnValue = DB2_MF;
        } else if ("Informix".equalsIgnoreCase(db)) {
            returnValue = INFORMIX;
        }
        return returnValue;
    }
}
