package cn.webfuse.framework.entity;

import java.util.Date;

public abstract class DataEntity<ID> extends BaseEntity {

    protected ID id;
    protected String remarks;
    protected Date createTime;
    protected Date updateTime;
    protected int deleted;

    public DataEntity() {
        this.createTime = new Date();
        this.updateTime = new Date();
        this.deleted = 0;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public abstract ID getId();

    public abstract void setId(ID id);

    public boolean isNew() {
        return null == getId();
    }


}
