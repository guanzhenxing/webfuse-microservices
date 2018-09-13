package cn.webfuse.system.dictionary.entity;

import cn.webfuse.framework.model.DataModel;

import java.io.Serializable;

/**
 * 字典类型
 */
public class DictionaryType extends DataModel<Integer> implements Serializable {

    private String code;    //字典类型编码

    private String name;    //字典类型名称

    private String description; //描述

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
