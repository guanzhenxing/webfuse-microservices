package cn.webfuse.data.mybatis.utils;

import cn.webfuse.common.kit.StringKits;
import cn.webfuse.data.mybatis.metadata.ColumnInfo;
import cn.webfuse.data.mybatis.metadata.GeneratorType;
import cn.webfuse.data.mybatis.metadata.IdentityDialect;
import cn.webfuse.data.mybatis.metadata.TableInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MetaDataHelper {

    /**
     * 实体类 => 表对象
     */
    private static final Map<Class<?>, TableInfo> classTableBinderMap = new ConcurrentHashMap<>();


    public static TableInfo getTableBinder(Class<?> entityClass) {
        TableInfo tableInfo = classTableBinderMap.get(entityClass);
        if (tableInfo != null) {
            return tableInfo;
        }
        initTableInfo(entityClass);
        if (null == classTableBinderMap.get(entityClass)) {
            throw new RuntimeException("无法获取实体类" + entityClass.getCanonicalName() + "对应的表名!");
        }
        return classTableBinderMap.get(entityClass);
    }

    public static synchronized void initTableInfo(Class<?> entityClass) {

        TableInfo tableInfo = classTableBinderMap.get(entityClass);
        if (tableInfo != null) {
            return;
        }

        tableInfo = new TableInfo();

        buildBasicTableInfo(entityClass, tableInfo);
        buildColumnsInfo(entityClass, tableInfo);

        classTableBinderMap.put(entityClass, tableInfo);

    }

    /**
     * 构建基础的表格信息
     *
     * @param entityClass
     * @param tableInfo
     */
    private static void buildBasicTableInfo(Class<?> entityClass, TableInfo tableInfo) {
        if (entityClass.isAnnotationPresent(Table.class)) {
            Table table = entityClass.getAnnotation(Table.class);
            if (StringUtils.isNotEmpty(table.name())) {
                tableInfo.setName(table.name());
            } else {
                tableInfo.setName(StringKits.UpperCamelToLowerUnderscore(entityClass.getSimpleName()));
            }
            tableInfo.setCatalog(table.catalog());
            tableInfo.setSchema(table.schema());
        }
    }

    private static void buildColumnsInfo(Class<?> entityClass, TableInfo tableInfo) {
        List<Field> fieldList = FieldUtils.getAllFieldsList(entityClass);
        Set<ColumnInfo> columnInfoSet = fieldList.stream().map(field -> {
            if (field.isAnnotationPresent(Transient.class)) {
                return null;
            }
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setJavaType(field.getType());
            columnInfo.setProperty(field.getName());
            setColumnName(field, columnInfo);
            setColumnId(field, columnInfo);

            return columnInfo;
        }).filter(columnInfo -> columnInfo != null).collect(Collectors.toSet());

        tableInfo.setColumns(columnInfoSet);
    }


    private static void setColumnName(Field field, ColumnInfo columnInfo) {
        String columnName = StringKits.UpperCamelToLowerUnderscore(field.getName());
        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            columnName = column.name();
        }
        columnInfo.setColumn(columnName.toLowerCase());
    }


    private static void setColumnId(Field field, ColumnInfo columnInfo) {
        if (field.isAnnotationPresent(Id.class)) {
            columnInfo.setIdColumn(true);
        }

        if (field.isAnnotationPresent(SequenceGenerator.class)) {
            SequenceGenerator sequenceGenerator = field.getAnnotation(SequenceGenerator.class);
            if (sequenceGenerator.sequenceName().equals("")) {
                throw new RuntimeException(field.getName() + "的注解@SequenceGenerator未指定sequenceName!");
            }
            columnInfo.setSequenceName(sequenceGenerator.sequenceName());
        }
        if (field.isAnnotationPresent(GeneratedValue.class)) {
            GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
            String generator = generatedValue.generator();
            GenerationType strategy = generatedValue.strategy();

            if (strategy == GenerationType.SEQUENCE) {  //序列
                columnInfo.setGeneratorType(GeneratorType.SEQUENCE);
            } else if (strategy == GenerationType.AUTO) {   //自动的
                switch (generator) {
                    case "":
                        columnInfo.setGeneratorType(GeneratorType.NONE);
                        break;
                    case "ID_WORKER":
                        columnInfo.setGeneratorType(GeneratorType.ID_WORKER);
                        break;
                    case "UUID":
                        columnInfo.setGeneratorType(GeneratorType.UUID);
                        break;
                    case "OBJECT_ID":
                        columnInfo.setGeneratorType(GeneratorType.OBJECT_ID);
                        break;
                    default:
                        throw new RuntimeException("");
                }
            } else if (strategy == GenerationType.IDENTITY) {
                columnInfo.setGeneratorType(GeneratorType.IDENTITY);
                if (StringUtils.isNotEmpty(generator)) {
                    IdentityDialect identityDialect = IdentityDialect.getIdentityDialect(generator);
                    if (null != identityDialect) {
                        generator = identityDialect.getIdentityRetrievalStatement();
                    }
                    columnInfo.setGenerator(generator);
                }
            } else {
                throw new RuntimeException("");
            }
        }
    }


}
