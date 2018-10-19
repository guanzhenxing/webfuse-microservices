package cn.webfuse.admin.upms.repository.model;

import cn.webfuse.framework.model.AbstractBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Actions extends AbstractBaseDO {

    private static final long serialVersionUID = 1L;

    @Id
    protected Long id;

    private String name;

    private String code;

    private String remark;

    private String status;


}
