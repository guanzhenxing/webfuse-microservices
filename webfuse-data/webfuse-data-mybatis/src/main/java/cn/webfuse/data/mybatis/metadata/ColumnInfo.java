package cn.webfuse.data.mybatis.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInfo {

    private TableInfo table;
    private String property;
    private String column;
    private Class<?> javaType;
    private Class<?> clazz;
    private boolean idColumn;
    private GeneratorType generatorType;
    private String sequenceName;
    private String generator;

//    private String orderBy;   // 暂时不知道怎么实现的orderBy


}
