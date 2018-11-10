package cn.webfuse.framework.data.mybatis.pageable;

import org.apache.ibatis.session.RowBounds;

/**
 * 可以记录 total 的分页参数
 * <p>
 * copy and modify from https://github.com/mybatis-book/book
 *
 * @author liuzh
 */
public class PageRowBounds extends RowBounds {

    private long total;

    public PageRowBounds() {
        super();
    }

    public PageRowBounds(int offset, int limit) {
        super(offset, limit);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
