package com.thuannd.crawler.fxssi.facade;

import com.thuannd.crawler.fxssi.dao.CurrentRatioDAO;
import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.model.CurrentRatioResponse;
import com.thuannd.crawler.fxssi.model.Page;
import com.thuannd.crawler.fxssi.model.SearchCurrentRatioRequest;
import com.thuannd.crawler.fxssi.service.CurrentRatioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CurrentRatioFacade {

    private final CurrentRatioService currentRatioService;
    private final CurrentRatioDAO currentRatioDAO;

    public void add(CurrentRatio currentRatio) {
        currentRatio.setId(String.valueOf(System.currentTimeMillis()));
        currentRatioService.add(currentRatio);
    }

    // TODO
    public Page<CurrentRatioResponse> searchCurrentRatio(SearchCurrentRatioRequest searchCurrentRatioRequest) {
        validateSearchCurrentRatioReq(searchCurrentRatioRequest);
        return currentRatioDAO.search(searchCurrentRatioRequest);
    }

    // TODO: add rule validate here
    private void validateSearchCurrentRatioReq(SearchCurrentRatioRequest searchCurrentRatioRequest) {
    }

}
