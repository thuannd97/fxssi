package com.thuannd.crawler.fxssi.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuannd.crawler.fxssi.client.FxssiClient;
import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.model.CurrentRatioResponse;
import com.thuannd.crawler.fxssi.utils.Constants;
import com.thuannd.crawler.fxssi.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.common.SolrDocument;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class CurrentRatioMapper {

    private final ObjectMapper objectMapper;

    public CurrentRatioResponse from(SolrDocument doc) {
        CurrentRatioResponse response = new CurrentRatioResponse();
        Object obj = doc.getFieldValue(Constants.ID);
        if (obj != null) {
            response.setId(obj.toString());
        }
        obj = doc.getFieldValue(Constants.SERVER_TIME);
        if (obj != null) {
            response.setServerTime(Utils.fromMillisecond(Long.parseLong(obj.toString())));
        }
        obj = doc.getFieldValue(Constants.CREATED_DATE);
        if (obj != null) {
            response.setCreatedDate(Utils.fromMillisecond(Long.parseLong(obj.toString())));
        }
        try {
            setCurrentRatioData(response, doc);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return response;
    }

    private void setCurrentRatioData(CurrentRatioResponse response, SolrDocument doc) throws JsonProcessingException {
        Map<String, Map<String, String>> ratioData = new HashMap<>();

        mapRatioData(doc, ratioData, Constants.AUDJPY);
        mapRatioData(doc, ratioData, Constants.AUDUSD);
        mapRatioData(doc, ratioData, Constants.EURJPY);
        mapRatioData(doc, ratioData, Constants.EURUSD);
        mapRatioData(doc, ratioData, Constants.GBPJPY);
        mapRatioData(doc, ratioData, Constants.GBPUSD);
        mapRatioData(doc, ratioData, Constants.NZDUSD);
        mapRatioData(doc, ratioData, Constants.USDCAD);
        mapRatioData(doc, ratioData, Constants.USDCHF);
        mapRatioData(doc, ratioData, Constants.USDJPY);
        mapRatioData(doc, ratioData, Constants.USDX);
        mapRatioData(doc, ratioData, Constants.XAUUSD);
        mapRatioData(doc, ratioData, Constants.XAUUSD);

        response.setData(ratioData);
    }

    private void mapRatioData(SolrDocument doc, Map<String, Map<String, String>> ratioData,
                              String current) throws JsonProcessingException {
        Object obj = doc.getFieldValue(current);
        if (obj != null) {
            ratioData.put(Constants.AUDJPY, objectMapper.readValue(obj.toString(), new TypeReference<>() {
            }));
        }
    }

    public CurrentRatio fromFxssiServerToCurrentRatio(FxssiClient.Response response) {
        CurrentRatio currentRatio = new CurrentRatio();
        currentRatio.setId(String.valueOf(System.currentTimeMillis()));
        currentRatio.setServerTime(response.getServerTime());
        currentRatio.setServerTextTime(response.getServerTextTime());
        currentRatio.setCreatedDate(System.currentTimeMillis());

        Map<String, Map<String, String>> pairs = response.getPairs();
        if (CollectionUtils.isEmpty(pairs)) return null;
        Map<String, String> currentRatioData = new HashMap<>();
        pairs.forEach((k, v) -> {
            try {
                log.info("time: {} -  put {} data to solr - value {}", LocalDateTime.now(), k, objectMapper.writeValueAsString(v));
                currentRatioData.put(k.toLowerCase(), objectMapper.writeValueAsString(v));
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        });
        currentRatio.setData(currentRatioData);

        return currentRatio;
    }

}
