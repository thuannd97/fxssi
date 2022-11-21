package com.thuannd.crawler.fxssi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRequest implements Serializable {

    private static final int MAX_10 = 10;

    private int pageNumber;
    private int pageSize;

    public SearchRequest(){
        this.pageSize = MAX_10;
    }

}
