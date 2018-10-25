package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.UserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.UserRole;
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
public interface UserRoleMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<UserRole> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserRoleResult")
    UserRole selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    List<UserRole> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(userRole);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, userRole);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, userRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(UserRole record) {
        return insert(SqlBuilder.insert(record)
                .into(userRole)
                .map(id).toProperty("id")
                .map(userId).toProperty("userId")
                .map(roleId).toProperty("roleId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(UserRole record) {
        return insert(SqlBuilder.insert(record)
                .into(userRole)
                .map(id).toProperty("id")
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserRole>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, userId, roleId)
                .from(userRole);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserRole>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, userId, roleId)
                .from(userRole);
    }


    default UserRole selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, userId, roleId)
                .from(userRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId);
    }


    default int updateByPrimaryKey(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}