package cn.webfuse.data.mybatis.mapper.provider;

import java.util.Map;

public class BaseProvider<T, ID> {

    public String insert(T entity) {
        return null;
    }

    public String insertInBatch(Iterable<T> entities) {
        return null;
    }

    public String deleteById(ID id) {
        return null;
    }

    public String delete(T entity) {
        return null;
    }

    public String deleteInBatch(Iterable<? extends T> entities) {
        return null;
    }

    public String deleteAll() {
        return null;
    }

    public String update(T entity) {
        return null;
    }

    public String updateByExample(T entity, Object example) {
        return null;
    }

    public String selectById(ID id) {
        return null;
    }

    public String selectAll() {
        return null;
    }

    public String selectAllById(Iterable<ID> ids) {
        return null;
    }

    public String selectOne(Object example) {
        return null;
    }

    public String selectMap(Map<String, Object> columnMap) {
        return null;
    }

    public String select(Object example) {
        return null;
    }

    public String count(Object example) {
        return null;
    }

    public String countAll() {
        return null;
    }
}
