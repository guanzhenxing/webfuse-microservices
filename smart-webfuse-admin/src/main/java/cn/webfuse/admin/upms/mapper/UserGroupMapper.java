package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.UserGroupDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.UserGroup;
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
public interface UserGroupMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<UserGroup> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserGroupResult")
    UserGroup selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserGroupResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.BIGINT)
    })
    List<UserGroup> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(userGroup);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, userGroup);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, userGroup)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(UserGroup record) {
        return insert(SqlBuilder.insert(record)
                .into(userGroup)
                .map(id).toProperty("id")
                .map(userId).toProperty("userId")
                .map(groupId).toProperty("groupId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(UserGroup record) {
        return insert(SqlBuilder.insert(record)
                .into(userGroup)
                .map(id).toProperty("id")
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(groupId).toPropertyWhenPresent("groupId", record::getGroupId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserGroup>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, userId, groupId)
                .from(userGroup);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserGroup>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, userId, groupId)
                .from(userGroup);
    }


    default UserGroup selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, userId, groupId)
                .from(userGroup)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(UserGroup record) {
        return UpdateDSL.updateWithMapper(this::update, userGroup)
                .set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(groupId).equalTo(record::getGroupId);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(UserGroup record) {
        return UpdateDSL.updateWithMapper(this::update, userGroup)
                .set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(groupId).equalToWhenPresent(record::getGroupId);
    }


    default int updateByPrimaryKey(UserGroup record) {
        return UpdateDSL.updateWithMapper(this::update, userGroup)
                .set(userId).equalTo(record::getUserId)
                .set(groupId).equalTo(record::getGroupId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(UserGroup record) {
        return UpdateDSL.updateWithMapper(this::update, userGroup)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(groupId).equalToWhenPresent(record::getGroupId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}