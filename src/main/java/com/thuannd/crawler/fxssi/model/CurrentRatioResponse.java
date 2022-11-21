package com.thuannd.crawler.fxssi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentRatioResponse implements Serializable {

    private String id;
    private LocalDateTime serverTime;
    private LocalDateTime createdDate;
    private Map<String, Map<String, String>> data;

}
