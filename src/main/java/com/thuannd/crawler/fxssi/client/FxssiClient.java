package com.thuannd.crawler.fxssi.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.mapper.CurrentRatioMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

@Slf4j
@Component
public class FxssiClient {

    private final String fxssiUrl;
    private final RestTemplate restTemplate;
    private final CurrentRatioMapper currentRatioMapper;

    @Autowired
    public FxssiClient(@Value("${fxssi.url}") String fxssiUrl, RestTemplate restTemplate,
                       CurrentRatioMapper currentRatioMapper) {
        this.fxssiUrl = fxssiUrl;
        this.restTemplate = restTemplate;
        this.currentRatioMapper = currentRatioMapper;
    }

    public CurrentRatio getDataFromFxssi() {
        try {
            log.info("start get current-ratio data from: {}", fxssiUrl);
            Response response = restTemplate.getForObject(fxssiUrl, Response.class);
            if (response == null) {
                log.info("response from fxssi is null or empty!");
                return null;
            }
            return currentRatioMapper.fromFxssiServerToCurrentRatio(response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * "pairs": {
     * "AUDJPY": {
     * "dukscopy": "33.97",
     * "fiboforx": "39.00",
     * },
     * "AUDUSD": {
     * "dukscopy": "62.81",
     * "fiboforx": "11.00",
     * }
     * }
     */
    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response implements Serializable {

        @JsonProperty("server_time")
        private Long serverTime;
        @JsonProperty("server_time_text")
        private String serverTextTime;
        @JsonProperty("pairs")
        private Map<String, Map<String, String>> pairs;

    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PairsObject implements Serializable {

        private Map<String, Map<String, String>> pairs;

    }

}
