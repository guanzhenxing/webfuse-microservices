package cn.webfuse.data.mybatis.mapper;

import cn.webfuse.data.mybatis.conditions.Predicate;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {

    //TODO 根据主键进行查询、删除以及修改

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteById")
    int deleteById(@Param("id") Serializable id);


    /**
     * 根据 conditions 条件，删除记录
     *
     * @param queryPredicate 实体对象封装操作类（可以为 null）
     */
    @DeleteProvider(type = BaseProvider.class, method = "delete")
    int delete(@Param("wrapper") Predicate<T> queryPredicate);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteByIds")
    int deleteByIds(@Param("collection") Collection<? extends Serializable> idList);

    /**
     * 根据 ID 修改
     *
     * @param model 实体对象
     */
    @UpdateProvider(type = BaseProvider.class, method = "updateById")
    int updateById(@Param("conditions") T model);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param model           实体对象 (set 条件值,不能为 null)
     * @param updatePredicate 实体对象封装操作类（可以为 null,里面的 conditions 用于生成 where 语句）
     */
    @UpdateProvider(type = BaseProvider.class, method = "update")
    int update(@Param("conditions") T model, @Param("wrapper") Predicate<T> updatePredicate);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    @SelectProvider(type = BaseProvider.class, method = "selectById")
    T selectById(@Param("id") Serializable id);

    /**
     * 根据 conditions 条件，查询一条记录
     *
     * @param queryPredicate 实体对象
     */
    @SelectProvider(type = BaseProvider.class, method = "selectOne")
    T selectOne(@Param("wrapper") Predicate<T> queryPredicate);

    /**
     * 根据 conditions 条件，查询一条记录
     *
     * @param queryPredicate 实体对象
     */
    @SelectProvider(type = BaseProvider.class, method = "selectOneForMap")
    Map<String, Object> selectOneForMap(@Param("wrapper") Predicate<T> queryPredicate);


    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    @SelectProvider(type = BaseProvider.class, method = "selectByIds")
    List<T> selectByIds(@Param("collection") Collection<? extends Serializable> idList);


    /**
     * 根据 conditions 条件，查询全部记录
     *
     * @param queryPredicate 实体对象封装操作类（可以为 null）
     */
    @SelectProvider(type = BaseProvider.class, method = "select")
    List<T> select(@Param("wrapper") Predicate<T> queryPredicate);

    /**
     * 根据 conditions 条件，查询全部记录（并翻页）
     *
     * @param queryPredicate 实体对象封装操作类（可以为 null）
     * @param start          分页起始值
     * @param limit          分页条数
     */
    @SelectProvider(type = BaseProvider.class, method = "selectByPageable")
    List<T> selectPageable(@Param("wrapper") Predicate<T> queryPredicate, @Param("start") int start, @Param("limit") int limit);

    /**
     * 根据 Predicate 条件，查询全部记录
     *
     * @param queryPredicate 实体对象封装操作类（可以为 null）
     */
    @SelectProvider(type = BaseProvider.class, method = "selectForMapList")
    List<Map<String, Object>> selectForMapList(@Param("wrapper") Predicate<T> queryPredicate);

    /**
     * 根据 Predicate 条件，查询全部记录（并翻页）
     *
     * @param queryPredicate 实体对象封装操作类（可以为 null）
     * @param start          分页起始值
     * @param limit          分页条数
     */
    @SelectProvider(type = BaseProvider.class, method = "selectForMapListByPageable")
    List<Map<String, Object>> selectForMapListPageable(@Param("wrapper") Predicate<T> queryPredicate, @Param("start") int start, @Param("limit") int limit);

    /**
     * 根据 Predicate 条件，查询总记录数
     *
     * @param queryPredicate 实体对象
     */
    @SelectProvider(type = BaseProvider.class, method = "count")
    Long count(@Param("wrapper") Predicate<T> queryPredicate);


    /**
     * 插入一条记录
     *
     * @param model 实体对象
     */
    @InsertProvider(type = BaseProvider.class, method = "insert")
    int insert(@Param("conditions") T model);
}