package com.thuannd.crawler.fxssi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@SolrDocument(collection = "current-ratio")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentRatio implements Serializable {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;
    @Indexed(name = "pairs", type = "text_general")
    private String pairs;
    @Indexed(name = "server_time", type="plong")
    private Long serverTime;
    @Indexed(name = "createdDate", type="plong")
    private Long createdDate;
    @Indexed(name = "server_text_time", type="string")
    private String serverTextTime;
    @Dynamic
    @Field("*_s")
    private Map<String, String> data;

}
