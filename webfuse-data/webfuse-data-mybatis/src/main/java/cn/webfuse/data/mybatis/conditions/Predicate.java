package cn.webfuse.data.mybatis.conditions;


import cn.webfuse.data.mybatis.metadata.ColumnInfo;
import cn.webfuse.data.mybatis.metadata.TableInfo;
import cn.webfuse.data.mybatis.utils.MetaDataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 */
public class Predicate<T> {
    protected String orderByClause;

    protected boolean distinct;

    protected boolean exists;

    protected List<Criteria> oredCriteria;

    protected T entityClass;

    protected TableInfo table;
    //属性和列对应
    protected Map<String, ColumnInfo> propertyColumnBinderMap;

    /**
     * 默认exists为false
     *
     * @param entityClass
     */
    public Predicate(T entityClass) {
        this(entityClass, false);
    }

    /**
     * 带exists参数的构造方法
     *
     * @param entityClass
     * @param exists      - true时，如果字段不存在就抛出异常，false时，如果不存在就不使用该字段的条件
     */
    public Predicate(T entityClass, boolean exists) {
        this.exists = exists;
        oredCriteria = new ArrayList<>();
        this.entityClass = entityClass;
        table = MetaDataHelper.getTableBinder((Class<?>) entityClass);
        propertyColumnBinderMap = table.getColumns().stream().collect(
                Collectors.toMap(ColumnInfo::getProperty, Function.identity())
        );
    }

    public T getEntityClass() {
        return entityClass;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(propertyColumnBinderMap, exists);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected boolean exists;  //字段是否必须存在

        protected Map<String, ColumnInfo> propertyColumnBinderMap;    //属性和列对应

        protected GeneratedCriteria(Map<String, ColumnInfo> propertyColumnBinderMap) {
            this(propertyColumnBinderMap, true);
        }

        protected GeneratedCriteria(Map<String, ColumnInfo> propertyColumnBinderMap, boolean exists) {
            super();
            this.exists = exists;
            criteria = new ArrayList<>();
            this.propertyColumnBinderMap = propertyColumnBinderMap;
        }

        private String column(String property) {
            if (propertyColumnBinderMap.containsKey(property)) {
                return propertyColumnBinderMap.get(property).getColumn();
            } else if (exists) {
                throw new RuntimeException("This entity not contains property of " + property);
            } else {
                return null;
            }
        }

        private String property(String property) {
            if (propertyColumnBinderMap.containsKey(property)) {
                return property;
            } else if (exists) {
                throw new RuntimeException("This entity not contains property of " + property);
            } else {
                return null;
            }
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            if (condition.startsWith("null")) {
                return;
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            if (property == null) {
                return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            if (property == null) {
                return;
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIsNull(String property) {
            addCriterion(column(property) + " is null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(String property) {
            addCriterion(column(property) + " is not null");
            return (Criteria) this;
        }

        public Criteria andEqualTo(String property, Object value) {
            addCriterion(column(property) + " =", value, property(property));
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String property, Object value) {
            addCriterion(column(property) + " <>", value, property(property));
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String property, Object value) {
            addCriterion(column(property) + " >", value, property(property));
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String property, Object value) {
            addCriterion(column(property) + " >=", value, property(property));
            return (Criteria) this;
        }

        public Criteria andLessThan(String property, Object value) {
            addCriterion(column(property) + " <", value, property(property));
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String property, Object value) {
            addCriterion(column(property) + " <=", value, property(property));
            return (Criteria) this;
        }

        public Criteria andIn(String property, List<Object> values) {
            addCriterion(column(property) + " in", values, property(property));
            return (Criteria) this;
        }

        public Criteria andNotIn(String property, List<Object> values) {
            addCriterion(column(property) + " not in", values, property(property));
            return (Criteria) this;
        }

        public Criteria andBetween(String property, Object value1, Object value2) {
            addCriterion(column(property) + " between", value1, value2, property(property));
            return (Criteria) this;
        }

        public Criteria andNotBetween(String property, Object value1, Object value2) {
            addCriterion(column(property) + " not between", value1, value2, property(property));
            return (Criteria) this;
        }

        public Criteria andLike(String property, String value) {
            addCriterion(column(property) + "  like", value, property(property));
            return (Criteria) this;
        }

        public Criteria andNotLike(String property, String value) {
            addCriterion(column(property) + "  not like", value, property(property));
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria(Map<String, ColumnInfo> propertyMap) {
            super(propertyMap);
        }

        protected Criteria(Map<String, ColumnInfo> propertyMap, boolean exists) {
            super(propertyMap, exists);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

}