package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.PermissionActionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.PermissionAction;
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
public interface PermissionActionMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<PermissionAction> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PermissionActionResult")
    PermissionAction selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PermissionActionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="action_id", property="actionId", jdbcType=JdbcType.BIGINT)
    })
    List<PermissionAction> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(permissionAction);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, permissionAction);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, permissionAction)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(PermissionAction record) {
        return insert(SqlBuilder.insert(record)
                .into(permissionAction)
                .map(id).toProperty("id")
                .map(permissionId).toProperty("permissionId")
                .map(actionId).toProperty("actionId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(PermissionAction record) {
        return insert(SqlBuilder.insert(record)
                .into(permissionAction)
                .map(id).toProperty("id")
                .map(permissionId).toPropertyWhenPresent("permissionId", record::getPermissionId)
                .map(actionId).toPropertyWhenPresent("actionId", record::getActionId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PermissionAction>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, permissionId, actionId)
                .from(permissionAction);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PermissionAction>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, permissionId, actionId)
                .from(permissionAction);
    }


    default PermissionAction selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, permissionId, actionId)
                .from(permissionAction)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(PermissionAction record) {
        return UpdateDSL.updateWithMapper(this::update, permissionAction)
                .set(id).equalTo(record::getId)
                .set(permissionId).equalTo(record::getPermissionId)
                .set(actionId).equalTo(record::getActionId);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(PermissionAction record) {
        return UpdateDSL.updateWithMapper(this::update, permissionAction)
                .set(id).equalToWhenPresent(record::getId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .set(actionId).equalToWhenPresent(record::getActionId);
    }


    default int updateByPrimaryKey(PermissionAction record) {
        return UpdateDSL.updateWithMapper(this::update, permissionAction)
                .set(permissionId).equalTo(record::getPermissionId)
                .set(actionId).equalTo(record::getActionId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(PermissionAction record) {
        return UpdateDSL.updateWithMapper(this::update, permissionAction)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .set(actionId).equalToWhenPresent(record::getActionId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}