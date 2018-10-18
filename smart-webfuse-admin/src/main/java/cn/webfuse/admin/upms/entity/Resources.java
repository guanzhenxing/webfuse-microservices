package cn.webfuse.admin.upms.entity;

import cn.webfuse.admin.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Resources extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String type;

    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;


}
