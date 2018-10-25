package cn.webfuse.data.mybatis.mapper;

import cn.webfuse.data.mybatis.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * 定义基本的操作.
 *
 * @param <T>
 */
public interface BaseMapper<T, ID> extends Mapper<T, ID> {

    @InsertProvider(type = BaseProvider.class, method = "insert")
    int insert(@Param("entity") T entity);

    @InsertProvider(type = BaseProvider.class, method = "insertInBatch")
    int insertInBatch(@Param("collection") Iterable<T> entities);

    @DeleteProvider(type = BaseProvider.class, method = "deleteById")
    int deleteById(@Param("id") ID id);

    @DeleteProvider(type = BaseProvider.class, method = "delete")
    int delete(@Param("entity") T entity);

    @DeleteProvider(type = BaseProvider.class, method = "deleteInBatch")
    int deleteInBatch(@Param("collection") Iterable<? extends T> entities);

    @DeleteProvider(type = BaseProvider.class, method = "deleteAll")
    int deleteAll();

    @UpdateProvider(type = BaseProvider.class, method = "update")
    int update(@Param("entity") T entity);

    @UpdateProvider(type = BaseProvider.class, method = "updateByExample")
    int updateByExample(@Param("entity") T entity, @Param("example") Object example);

    @SelectProvider(type = BaseProvider.class, method = "selectById")
    T selectById(@Param("id") ID id);

    @SelectProvider(type = BaseProvider.class, method = "selectAll")
    Iterable<T> selectAll();

    @SelectProvider(type = BaseProvider.class, method = "selectAllById")
    Iterable<T> selectAllById(@Param("collection") Iterable<ID> ids);

    @SelectProvider(type = BaseProvider.class, method = "selectOne")
    T selectOne(@Param("example") Object example);

    @SelectProvider(type = BaseProvider.class, method = "selectMap")
    Iterable<Map<String, Object>> selectMap(@Param("map") Map<String, Object> columnMap);

    @SelectProvider(type = BaseProvider.class, method = "select")
    Iterable<T> select(@Param("example") Object example);

    @SelectProvider(type = BaseProvider.class, method = "count")
    long count(@Param("example") Object example);

    @SelectProvider(type = BaseProvider.class, method = "countAll")
    long countAll();


}
