package cn.webfuse.framework.entity;

import java.util.Date;

public abstract class DataEntity<ID> extends BaseEntity {

    protected String remarks;
    protected Date create_time;
    protected Date update_time;
    protected int deleted;

    public DataEntity() {
        this.create_time = new Date();
        this.update_time = new Date();
        this.deleted = 0;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public abstract ID getId();

    public abstract void setId();

    public boolean isNew() {
        return null == getId();
    }


}
