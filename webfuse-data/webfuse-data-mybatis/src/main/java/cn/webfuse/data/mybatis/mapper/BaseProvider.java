package cn.webfuse.data.mybatis.mapper;

import cn.webfuse.data.mybatis.conditions.Predicate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class BaseProvider<T> {


    public String deleteById(final Serializable id) {
        return null;
    }

    public String deleteByMap(final Map<String, Object> columnMap) {
        return null;
    }

    public String delete(final Predicate<T> queryPredicate) {
        return null;
    }

    public String deleteByIds(final Collection<? extends Serializable> idList) {
        return null;
    }

    public String updateById(final T model) {
        return null;
    }

    public String update(T model, Predicate<T> queryPredicate) {
        return null;
    }

    public String selectById(Serializable id) {
        return null;
    }

    public String selectOne(Predicate<T> queryPredicate) {
        return null;
    }

    public String selectOneForMap(Predicate<T> queryPredicate) {
        return null;
    }

    public String selectByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    public String selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    public String select(Predicate<T> queryPredicate) {
        return null;
    }

    public String selectByPageable(Predicate<T> queryPredicate, int start, int limit) {
        return null;
    }

    public String selectForMapList(Predicate<T> queryPredicate) {
        return null;
    }

    public String selectForMapListByPageable(Predicate<T> queryPredicate, int start, int limit) {
        return null;
    }

    public String count(Predicate<T> queryPredicate) {
        return null;
    }

    public String insert(T model) {
        return null;
    }

    //###############################################################//

    private Class<?> mapperClass;

    public BaseProvider() {
    }

    public BaseProvider(Class<?> mapperClass) {
        this.mapperClass = mapperClass;
    }
}
