package com.thuannd.crawler.fxssi.controller;

import com.thuannd.crawler.fxssi.api.CurrentRatioAPI;
import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.facade.CurrentRatioFacade;
import com.thuannd.crawler.fxssi.model.CurrentRatioResponse;
import com.thuannd.crawler.fxssi.model.Page;
import com.thuannd.crawler.fxssi.model.SearchCurrentRatioRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class CurrentRatioController implements CurrentRatioAPI {

    private final CurrentRatioFacade currentRatioFacade;

    @Override
    public void add(CurrentRatio currentRatio) {
        currentRatioFacade.add(currentRatio);
    }

    @Override
    public Page<CurrentRatioResponse> search(SearchCurrentRatioRequest req) {
        return currentRatioFacade.searchCurrentRatio(req);
    }

}
