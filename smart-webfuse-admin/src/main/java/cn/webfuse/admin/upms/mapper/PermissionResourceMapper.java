package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.PermissionResourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.PermissionResource;
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
public interface PermissionResourceMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<PermissionResource> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PermissionResourceResult")
    PermissionResource selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PermissionResourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT)
    })
    List<PermissionResource> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(permissionResource);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, permissionResource);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, permissionResource)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(PermissionResource record) {
        return insert(SqlBuilder.insert(record)
                .into(permissionResource)
                .map(id).toProperty("id")
                .map(permissionId).toProperty("permissionId")
                .map(resourceId).toProperty("resourceId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(PermissionResource record) {
        return insert(SqlBuilder.insert(record)
                .into(permissionResource)
                .map(id).toProperty("id")
                .map(permissionId).toPropertyWhenPresent("permissionId", record::getPermissionId)
                .map(resourceId).toPropertyWhenPresent("resourceId", record::getResourceId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PermissionResource>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, permissionId, resourceId)
                .from(permissionResource);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PermissionResource>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, permissionId, resourceId)
                .from(permissionResource);
    }


    default PermissionResource selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, permissionId, resourceId)
                .from(permissionResource)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(PermissionResource record) {
        return UpdateDSL.updateWithMapper(this::update, permissionResource)
                .set(id).equalTo(record::getId)
                .set(permissionId).equalTo(record::getPermissionId)
                .set(resourceId).equalTo(record::getResourceId);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(PermissionResource record) {
        return UpdateDSL.updateWithMapper(this::update, permissionResource)
                .set(id).equalToWhenPresent(record::getId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .set(resourceId).equalToWhenPresent(record::getResourceId);
    }


    default int updateByPrimaryKey(PermissionResource record) {
        return UpdateDSL.updateWithMapper(this::update, permissionResource)
                .set(permissionId).equalTo(record::getPermissionId)
                .set(resourceId).equalTo(record::getResourceId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(PermissionResource record) {
        return UpdateDSL.updateWithMapper(this::update, permissionResource)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .set(resourceId).equalToWhenPresent(record::getResourceId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}