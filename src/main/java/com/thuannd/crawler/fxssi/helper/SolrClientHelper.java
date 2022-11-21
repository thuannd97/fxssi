package com.thuannd.crawler.fxssi.helper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SolrClientHelper {

    private final SolrClient solrClient;

    public QueryResponse query(SolrQuery solrQuery, int pageSize, int pageNumber){
        try{
            solrQuery.setStart((pageNumber - 1) * pageSize);
            solrQuery.setRows(pageSize);
            return solrClient.query(solrQuery);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

}
