package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.RolesDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.Roles;
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
public interface RolesMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<Roles> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RolesResult")
    Roles selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RolesResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Roles> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(roles);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, roles);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, roles)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(Roles record) {
        return insert(SqlBuilder.insert(record)
                .into(roles)
                .map(id).toProperty("id")
                .map(code).toProperty("code")
                .map(name).toProperty("name")
                .map(status).toProperty("status")
                .map(type).toProperty("type")
                .map(remark).toProperty("remark")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(Roles record) {
        return insert(SqlBuilder.insert(record)
                .into(roles)
                .map(id).toProperty("id")
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Roles>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, code, name, status, type, remark)
                .from(roles);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Roles>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, code, name, status, type, remark)
                .from(roles);
    }


    default Roles selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, code, name, status, type, remark)
                .from(roles)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Roles record) {
        return UpdateDSL.updateWithMapper(this::update, roles)
                .set(id).equalTo(record::getId)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(status).equalTo(record::getStatus)
                .set(type).equalTo(record::getType)
                .set(remark).equalTo(record::getRemark);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Roles record) {
        return UpdateDSL.updateWithMapper(this::update, roles)
                .set(id).equalToWhenPresent(record::getId)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(type).equalToWhenPresent(record::getType)
                .set(remark).equalToWhenPresent(record::getRemark);
    }


    default int updateByPrimaryKey(Roles record) {
        return UpdateDSL.updateWithMapper(this::update, roles)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(status).equalTo(record::getStatus)
                .set(type).equalTo(record::getType)
                .set(remark).equalTo(record::getRemark)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(Roles record) {
        return UpdateDSL.updateWithMapper(this::update, roles)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(type).equalToWhenPresent(record::getType)
                .set(remark).equalToWhenPresent(record::getRemark)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}