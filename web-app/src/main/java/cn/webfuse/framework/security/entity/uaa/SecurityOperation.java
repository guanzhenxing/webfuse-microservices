package cn.webfuse.framework.security.entity.uaa;

/**
 * UAA的操作
 */
public class SecurityOperation {
    private String code;    // 全局唯一:包名.类名.函数
    private String tag;     // 资源标签
    private String name;    //名称
    private String description; //描述
    private String uri;// Restful api

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

