package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.ResourcesDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.Resources;
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
public interface ResourcesMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<Resources> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ResourcesResult")
    Resources selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ResourcesResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<Resources> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(resources);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, resources);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, resources)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(Resources record) {
        return insert(SqlBuilder.insert(record)
                .into(resources)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(type).toProperty("type")
                .map(code).toProperty("code")
                .map(remark).toProperty("remark")
                .map(status).toProperty("status")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(Resources record) {
        return insert(SqlBuilder.insert(record)
                .into(resources)
                .map(id).toProperty("id")
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Resources>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, type, code, remark, status)
                .from(resources);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Resources>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, type, code, remark, status)
                .from(resources);
    }


    default Resources selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, type, code, remark, status)
                .from(resources)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Resources record) {
        return UpdateDSL.updateWithMapper(this::update, resources)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(type).equalTo(record::getType)
                .set(code).equalTo(record::getCode)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Resources record) {
        return UpdateDSL.updateWithMapper(this::update, resources)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(type).equalToWhenPresent(record::getType)
                .set(code).equalToWhenPresent(record::getCode)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus);
    }


    default int updateByPrimaryKey(Resources record) {
        return UpdateDSL.updateWithMapper(this::update, resources)
                .set(name).equalTo(record::getName)
                .set(type).equalTo(record::getType)
                .set(code).equalTo(record::getCode)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(Resources record) {
        return UpdateDSL.updateWithMapper(this::update, resources)
                .set(name).equalToWhenPresent(record::getName)
                .set(type).equalToWhenPresent(record::getType)
                .set(code).equalToWhenPresent(record::getCode)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}