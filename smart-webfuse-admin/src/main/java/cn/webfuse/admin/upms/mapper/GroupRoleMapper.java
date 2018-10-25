package cn.webfuse.admin.upms.mapper;

import static cn.webfuse.admin.upms.mapper.GroupRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.webfuse.admin.upms.model.GroupRole;
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
public interface GroupRoleMapper {

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=true, resultType=Long.class)
    int insert(InsertStatementProvider<GroupRole> insertStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GroupRoleResult")
    GroupRole selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GroupRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    List<GroupRole> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(groupRole);
    }


    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, groupRole);
    }


    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, groupRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default int insert(GroupRole record) {
        return insert(SqlBuilder.insert(record)
                .into(groupRole)
                .map(id).toProperty("id")
                .map(groupId).toProperty("groupId")
                .map(roleId).toProperty("roleId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default int insertSelective(GroupRole record) {
        return insert(SqlBuilder.insert(record)
                .into(groupRole)
                .map(id).toProperty("id")
                .map(groupId).toPropertyWhenPresent("groupId", record::getGroupId)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<GroupRole>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, groupId, roleId)
                .from(groupRole);
    }


    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<GroupRole>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, groupId, roleId)
                .from(groupRole);
    }


    default GroupRole selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, groupId, roleId)
                .from(groupRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(GroupRole record) {
        return UpdateDSL.updateWithMapper(this::update, groupRole)
                .set(id).equalTo(record::getId)
                .set(groupId).equalTo(record::getGroupId)
                .set(roleId).equalTo(record::getRoleId);
    }


    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(GroupRole record) {
        return UpdateDSL.updateWithMapper(this::update, groupRole)
                .set(id).equalToWhenPresent(record::getId)
                .set(groupId).equalToWhenPresent(record::getGroupId)
                .set(roleId).equalToWhenPresent(record::getRoleId);
    }


    default int updateByPrimaryKey(GroupRole record) {
        return UpdateDSL.updateWithMapper(this::update, groupRole)
                .set(groupId).equalTo(record::getGroupId)
                .set(roleId).equalTo(record::getRoleId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    default int updateByPrimaryKeySelective(GroupRole record) {
        return UpdateDSL.updateWithMapper(this::update, groupRole)
                .set(groupId).equalToWhenPresent(record::getGroupId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}