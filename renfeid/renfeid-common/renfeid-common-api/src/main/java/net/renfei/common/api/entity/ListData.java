package net.renfei.common.api.entity;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

public class ListData<T> implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    private int pageNum = 1;
    private int pageSize = 10;
    private long startRow = 0;
    private long endRow = 0;
    private long total = 0;
    private int pages = 0;
    private List<T> data;

    public ListData() {
    }

    public ListData(Page<T> page) {
        pageNum = page.getPageNum();
        pageSize = page.getPageSize();
        startRow = page.getStartRow();
        endRow = page.getEndRow();
        total = page.getTotal();
        pages = page.getPages();
        data = page.getResult();
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setEndRow(long endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
