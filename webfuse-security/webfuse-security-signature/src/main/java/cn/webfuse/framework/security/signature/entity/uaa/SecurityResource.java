package cn.webfuse.framework.security.signature.entity.uaa;

/**
 * 资源
 */
public class SecurityResource {
    private Long id;    //资源的id
    private String type;    // 客户端类型，取值：app，web
    private String code;    // 资源标识符，客户端控制用，全局唯一
    private String tag;     // 资源标签
    private String name;    //名称
    private String description; //描述
    private Long parentId;  //父ID，资源有层级关系

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
