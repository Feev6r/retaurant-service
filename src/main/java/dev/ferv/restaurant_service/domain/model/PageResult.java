package dev.ferv.restaurant_service.domain.model;

import java.util.List;

public class PageResult<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;


    public PageResult(List<T> content, int page, int size, long totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
    }


    public List<T> getContent() {
        return content;
    }


    public int getPage() {
        return page;
    }


    public int getSize() {
        return size;
    }


    public long getTotalElements() {
        return totalElements;
    }  

}
