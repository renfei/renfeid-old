package net.renfei.model;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class ListData<T> {
    private int pageNum;
    private int pageSize;
    private long startRow;
    private long endRow;
    private long total;
    private int pages;
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
}
