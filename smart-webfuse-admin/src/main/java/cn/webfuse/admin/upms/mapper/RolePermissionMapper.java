package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.RolePermissionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.RolePermission;
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
public interface RolePermissionMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<RolePermission> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RolePermissionResult")
    RolePermission selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RolePermissionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT)
    })
    List<RolePermission> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rolePermission);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rolePermission);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rolePermission)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(RolePermission record) {
        return insert(SqlBuilder.insert(record)
                .into(rolePermission)
                .map(id).toProperty("id")
                .map(roleId).toProperty("roleId")
                .map(permissionId).toProperty("permissionId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(RolePermission record) {
        return insert(SqlBuilder.insert(record)
                .into(rolePermission)
                .map(id).toProperty("id")
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(permissionId).toPropertyWhenPresent("permissionId", record::getPermissionId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RolePermission>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, roleId, permissionId)
                .from(rolePermission);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RolePermission>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, roleId, permissionId)
                .from(rolePermission);
    }


    default RolePermission selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, roleId, permissionId)
                .from(rolePermission)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(id).equalTo(record::getId)
                .set(roleId).equalTo(record::getRoleId)
                .set(permissionId).equalTo(record::getPermissionId);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(id).equalToWhenPresent(record::getId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId);
    }


    default int updateByPrimaryKey(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(roleId).equalTo(record::getRoleId)
                .set(permissionId).equalTo(record::getPermissionId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}