package cn.webfuse.framework.web.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结构的返回值
 *
 * @param <T>
 */
public class PageableResponse<T> implements Serializable {

    private int page;
    private int size;
    private long total;
    private List<T> content;

    public PageableResponse(int page, int size, long total, List<T> content) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.content = content;
    }

    public PageableResponse() {
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    public long getTotalElements() {
        return total;
    }

    public boolean hasNext() {
        return getPage() + 1 < getTotalPages();
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean hasPrevious() {
        return getPage() > 0;
    }

    public boolean isFirst() {
        return !hasPrevious();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageableResponse<?> that = (PageableResponse<?>) o;

        if (page != that.page) return false;
        if (size != that.size) return false;
        if (total != that.total) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + size;
        result = 31 * result + (int) (total ^ (total >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
