package cn.webfuse.data.mybatis.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo {

    private String schema;
    private String catalog;
    private String name;
    private Set<ColumnInfo> columns;


}
