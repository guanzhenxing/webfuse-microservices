package cn.webfuse.system.dictionary.entity;

import cn.webfuse.framework.model.DataModel;

import java.io.Serializable;

/**
 * 字典
 */
public class Dictionary extends DataModel<Integer> implements Serializable {


    private String code;    //字典编码
    private String name;    //字典名称
    private Integer parentId;   //字典的父ID
    private String description; //描述
    private Integer sequence;   //排序
    private Integer enable;     // 是否可用：1=启用，0=禁用
    private Integer type;   //类型

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
