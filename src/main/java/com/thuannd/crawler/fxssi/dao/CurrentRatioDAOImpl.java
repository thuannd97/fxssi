package com.thuannd.crawler.fxssi.dao;

import com.thuannd.crawler.fxssi.helper.SolrClientHelper;
import com.thuannd.crawler.fxssi.mapper.CurrentRatioMapper;
import com.thuannd.crawler.fxssi.model.CurrentRatioResponse;
import com.thuannd.crawler.fxssi.model.Page;
import com.thuannd.crawler.fxssi.model.SearchCurrentRatioRequest;
import com.thuannd.crawler.fxssi.utils.Constants;
import com.thuannd.crawler.fxssi.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;

@Slf4j
@Service
@AllArgsConstructor
public class CurrentRatioDAOImpl implements CurrentRatioDAO {

    private final SolrClientHelper solrClientHelper;

    private final CurrentRatioMapper currentRatioMapper;

    @Override
    public Page<CurrentRatioResponse> search(SearchCurrentRatioRequest request) {
        Page<CurrentRatioResponse> page = new Page<>();
        page.setPageNumber(request.getPageNumber());
        try {
            QueryResponse resp = solrClientHelper.query(buildQuery(request), request.getPageSize(), request.getPageNumber());
            long totalItems = resp.getResults().getNumFound();
            page.computePageAvailable(totalItems, request.getPageSize());
            page.setTotalItems(totalItems);

            Iterator<SolrDocument> iterator = resp.getResults().iterator();
            while (iterator.hasNext()) {
                SolrDocument solrDocument = iterator.next();
                CurrentRatioResponse ratioResponse = currentRatioMapper.from(solrDocument);
                if (ratioResponse != null) {
                    page.getPageItems().add(ratioResponse);
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return page;
    }

    private SolrQuery buildQuery(SearchCurrentRatioRequest request) {
        StringBuilder builder = new StringBuilder();
        if (request.getFromDateTime() != null && request.getToDateTime() == null) {
            builder.append("server_time:(").append(Utils.fromLocalDateTime(request.getFromDateTime())).append(" TO *)");
        }
        if (request.getFromDateTime() != null && request.getToDateTime() != null) {
            builder.append(Constants.SERVER_TIME).append(":(").append(Utils.fromLocalDateTime(request.getFromDateTime())).append(" TO ")
                    .append(Utils.fromLocalDateTime(request.getToDateTime())).append(" )");
        }
        if (request.getFromDateTime() == null && request.getToDateTime() != null) {
            builder.append(Constants.SERVER_TIME).append(":(* TO ").append(Utils.fromLocalDateTime(request.getToDateTime())).append(")");
        }
        if (request.getFromDateTime() == null && request.getToDateTime() == null) {
            LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            builder.append(Constants.SERVER_TIME).append(":(").append(Utils.fromLocalDateTime(localDateTime)).append(" TO *)");
        }
        log.info("query current ratio: {}", builder);

        return new SolrQuery(builder.toString());
    }
}
