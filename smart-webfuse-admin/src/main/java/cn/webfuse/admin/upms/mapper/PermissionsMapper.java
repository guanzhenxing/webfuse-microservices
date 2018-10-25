package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.PermissionsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.Permissions;
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
public interface PermissionsMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<Permissions> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PermissionsResult")
    Permissions selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PermissionsResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Permissions> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(permissions);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, permissions);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, permissions)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(Permissions record) {
        return insert(SqlBuilder.insert(record)
                .into(permissions)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(code).toProperty("code")
                .map(remark).toProperty("remark")
                .map(status).toProperty("status")
                .map(type).toProperty("type")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(Permissions record) {
        return insert(SqlBuilder.insert(record)
                .into(permissions)
                .map(id).toProperty("id")
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Permissions>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, code, remark, status, type)
                .from(permissions);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Permissions>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, code, remark, status, type)
                .from(permissions);
    }


    default Permissions selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, code, remark, status, type)
                .from(permissions)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Permissions record) {
        return UpdateDSL.updateWithMapper(this::update, permissions)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(code).equalTo(record::getCode)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus)
                .set(type).equalTo(record::getType);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Permissions record) {
        return UpdateDSL.updateWithMapper(this::update, permissions)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(code).equalToWhenPresent(record::getCode)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(type).equalToWhenPresent(record::getType);
    }


    default int updateByPrimaryKey(Permissions record) {
        return UpdateDSL.updateWithMapper(this::update, permissions)
                .set(name).equalTo(record::getName)
                .set(code).equalTo(record::getCode)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus)
                .set(type).equalTo(record::getType)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(Permissions record) {
        return UpdateDSL.updateWithMapper(this::update, permissions)
                .set(name).equalToWhenPresent(record::getName)
                .set(code).equalToWhenPresent(record::getCode)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(type).equalToWhenPresent(record::getType)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}