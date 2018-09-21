package cn.webfuse.framework.jpa.repository;

import cn.webfuse.framework.jpa.exception.JpaExecuteException;
import cn.webfuse.framework.jpa.parameter.Operator;
import cn.webfuse.framework.jpa.parameter.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExpandJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExpandJpaRepository<T, ID> {


    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ?> entityInformation;

    public ExpandJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }


    @Override
    public T findOne(String condition, Object... objects) {
        Validate.notBlank(condition, "条件不能为空");
        T result = null;
        try {
            result = (T) createQuery(condition, objects).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<T> findAll(String condition, Object... objects) {
        return null;
    }

    @Override
    public List<T> findAll(Iterable<Predicate> predicates, Operator operator) {
        return null;
    }

    @Override
    public List<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort) {
        return null;
    }

    @Override
    public Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Iterable<Predicate> predicates, Operator operator) {
        return 0;
    }

    @Override
    public List<T> findAll(String condition, Sort sort, Object... objects) {
        return null;
    }

    @Override
    public Page<T> findAll(String condition, Pageable pageable, Object... objects) {
        return null;
    }

    @Override
    public long count(String condition, Object... objects) {
        return 0;
    }

    @Override
    public void deleteByIds(Iterable<ID> ids) {

    }

    @Override
    public Class<T> getEntityClass() {
        return null;
    }

    @Override
    public List<Map<String, Object>> nativeQuery4Map(String sql) {
        return null;
    }

    @Override
    public Page<Map> nativeQuery4Map(String sql, Pageable pageable) {
        return null;
    }

    @Override
    public Object nativeQuery4Object(String sql) {
        return null;
    }


    /**
     * 声明entityClass的查询
     */
    private Query createQuery(String condition, Sort sort, Object[] objects) {

        JpqlQueryHolder queryHolder = new JpqlQueryHolder(condition, sort, objects);

        return queryHolder.createQuery();
    }

    /**
     * 声明entityClass的查询
     */
    private Query createQuery(String condition, Object[] objects) {
        return createQuery(condition, null, objects);
    }

    private class JpqlQueryHolder {

        //别名
        private final String ALIAS = "x";

        //QUERY ALL
        private final String FIND_ALL_QUERY_STRING = "from %s " + ALIAS;

        //传入的condition 排除列表
        private final String[] IGNORE_CONSTAINS_CHARSEQUENCE = {"where", "WHERE", "from", "FROM"};

        private String condition = null;
        private Sort sort;
        private Object[] objects;
        private Iterable<Predicate> predicates;
        private Operator operator = Operator.AND;

        private JpqlQueryHolder(Iterable<Predicate> predicates, Operator operator, Sort sort) {
            this.predicates = predicates;
            this.operator = operator;
            this.sort = sort;
        }

        private JpqlQueryHolder(Iterable<Predicate> predicates, Operator operator) {
            this.operator = operator;
            this.predicates = predicates;
        }

        private JpqlQueryHolder(String condition, Sort sort, Object[] objects) {
            this(condition, objects);
            this.sort = sort;
        }

        private JpqlQueryHolder(String condition, Object[] objects) {

            if (StringUtils.startsWithAny(condition, IGNORE_CONSTAINS_CHARSEQUENCE)) {
                throw new JpaExecuteException("查询条件中只能包含WHERE条件表达式!");
            }
            this.condition = StringUtils.trimToNull(condition);
            this.objects = objects;
        }

        private Query createQuery() {
            StringBuilder sb = new StringBuilder();
            // select x from table
            sb.append(QueryUtils.getQueryString(FIND_ALL_QUERY_STRING, entityInformation.getEntityName()))
                    //where
                    .append(applyCondition());

            Query query = entityManager.createQuery(QueryUtils.applySorting(sb.toString(), sort, ALIAS));
            applyQueryParameter(query);
            return query;
        }

        private TypedQuery<Long> createCountQuery() {
            String ql = String.format(QueryUtils.COUNT_QUERY_STRING, ALIAS, "%s");
            ql = QueryUtils.getQueryString(ql, entityInformation.getEntityName());
            ql += applyCondition();

            TypedQuery<Long> query = entityManager.createQuery(ql, Long.class);
            applyQueryParameter(query);
            return query;
        }

        private List<String> map2Conditions() {
            if (predicates == null || !predicates.iterator().hasNext()) {
                return new ArrayList<>();
            }
            List<String> conditions = new ArrayList<>();

            Iterator<Predicate> iterator = predicates.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Predicate predicate = iterator.next();
                if (predicate.getKey() == null) {
                    continue;
                }
                conditions.add(predicate.toCondition(String.valueOf(index)));
                index++;
            }
            return conditions;
        }

        private String applyCondition() {
            List<String> conditions = map2Conditions();
            if (condition != null) {
                conditions.add(condition);
            }
            condition = StringUtils.join(conditions, " " + operator.name() + " ");
            return StringUtils.isEmpty(condition) ? "" : " where " + condition;
        }

        private void applyQueryParameter(Query query) {
            if (objects != null) {
                int i = 0;
                for (Object value : objects) {
                    i++;
                    query.setParameter(i, value);
                }
            }
            if (predicates != null && predicates.iterator().hasNext()) {
                int index = 0;
                Iterator<Predicate> iterator = predicates.iterator();
                while (iterator.hasNext()) {
                    Predicate predicate = iterator.next();
                    predicate.setParameter(query, String.valueOf(index));
                    index++;
                }
            }
        }
    }

}
