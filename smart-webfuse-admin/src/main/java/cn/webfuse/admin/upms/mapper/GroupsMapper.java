package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.GroupsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.Groups;
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
public interface GroupsMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<Groups> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GroupsResult")
    Groups selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GroupsResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Groups> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(groups);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, groups);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, groups)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(Groups record) {
        return insert(SqlBuilder.insert(record)
                .into(groups)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(remark).toProperty("remark")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(Groups record) {
        return insert(SqlBuilder.insert(record)
                .into(groups)
                .map(id).toProperty("id")
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Groups>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, remark)
                .from(groups);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Groups>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, remark)
                .from(groups);
    }


    default Groups selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, remark)
                .from(groups)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(remark).equalTo(record::getRemark);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(remark).equalToWhenPresent(record::getRemark);
    }


    default int updateByPrimaryKey(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(name).equalTo(record::getName)
                .set(remark).equalTo(record::getRemark)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(name).equalToWhenPresent(record::getName)
                .set(remark).equalToWhenPresent(record::getRemark)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}