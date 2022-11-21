package com.thuannd.crawler.fxssi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonIgnoreProperties
public class SearchCurrentRatioRequest extends SearchRequest {

    @JsonProperty("from")
    private LocalDateTime fromDateTime;
    @JsonProperty("to")
    private LocalDateTime toDateTime;

}
