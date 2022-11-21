package com.thuannd.crawler.fxssi.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@ComponentScan
@EnableSolrRepositories(
        basePackages = "com.thuannd.crawler.fxssi.repository")
public class SolrConfig {

    private final String solrHost;

    @Autowired
    public SolrConfig(@Value("${solr.host}") String solrHost) {
        this.solrHost = solrHost;
    }

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrHost).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }

}
