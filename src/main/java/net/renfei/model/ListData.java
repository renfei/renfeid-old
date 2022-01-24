package net.renfei.model;

import com.github.pagehelper.Page;

import java.util.List;

public class ListData<T> {
    private int pageNum = 1;
    private int pageSize = 10;
    private long startRow = 0;
    private long endRow = 0;
    private long total = 0;
    private int pages = 0;
    private List<T> data;

    public ListData() {
    }

    public ListData(Page page) {
        pageNum = page.getPageNum();
        pageSize = page.getPageSize();
        startRow = page.getStartRow();
        endRow = page.getEndRow();
        total = page.getTotal();
        pages = page.getPages();
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
