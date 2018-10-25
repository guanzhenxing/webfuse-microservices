package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.UsersDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.Users;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface UsersMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<Users> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UsersResult")
    Users selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UsersResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Users> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(users);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, users);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, users)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(Users record) {
        return insert(SqlBuilder.insert(record)
                .into(users)
                .map(id).toProperty("id")
                .map(username).toProperty("username")
                .map(password).toProperty("password")
                .map(email).toProperty("email")
                .map(phone).toProperty("phone")
                .map(status).toProperty("status")
                .map(remark).toProperty("remark")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(Users record) {
        return insert(SqlBuilder.insert(record)
                .into(users)
                .map(id).toProperty("id")
                .map(username).toPropertyWhenPresent("username", record::getUsername)
                .map(password).toPropertyWhenPresent("password", record::getPassword)
                .map(email).toPropertyWhenPresent("email", record::getEmail)
                .map(phone).toPropertyWhenPresent("phone", record::getPhone)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Users>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, username, password, email, phone, status, remark)
                .from(users);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Users>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, username, password, email, phone, status, remark)
                .from(users);
    }


    default Users selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, username, password, email, phone, status, remark)
                .from(users)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Users record) {
        return UpdateDSL.updateWithMapper(this::update, users)
                .set(id).equalTo(record::getId)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(email).equalTo(record::getEmail)
                .set(phone).equalTo(record::getPhone)
                .set(status).equalTo(record::getStatus)
                .set(remark).equalTo(record::getRemark);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Users record) {
        return UpdateDSL.updateWithMapper(this::update, users)
                .set(id).equalToWhenPresent(record::getId)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(remark).equalToWhenPresent(record::getRemark);
    }


    default int updateByPrimaryKey(Users record) {
        return UpdateDSL.updateWithMapper(this::update, users)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(email).equalTo(record::getEmail)
                .set(phone).equalTo(record::getPhone)
                .set(status).equalTo(record::getStatus)
                .set(remark).equalTo(record::getRemark)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(Users record) {
        return UpdateDSL.updateWithMapper(this::update, users)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(remark).equalToWhenPresent(record::getRemark)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}