package com.thuannd.crawler.fxssi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page<E> {

    private int pageNumber;
    private int pageSize = 10;
    private int pageAvailable;
    private long totalItems;
    private List<E> pageItems;

    public Page(List<E> pageItems, int pageSize, int pageNumber){
        this.pageItems = pageItems;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public void computePageAvailable(long totalItems, int pageSize){
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.pageAvailable = 0;
        if(totalItems % (long) this.pageSize == 0L){
            this.pageAvailable = (int) (totalItems / (long) this.pageSize);
        } else {
            this.pageAvailable = (int) (totalItems / (long) this.pageSize) + 1;
        }
    }

}
