package com.thuannd.crawler.fxssi.executor;

import com.thuannd.crawler.fxssi.client.FxssiClient;
import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.facade.CurrentRatioFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrentRatioCrawler extends ExecutorJob {

    private final FxssiClient fxssiClient;
    private final CurrentRatioFacade currentRatioFacade;

    @Autowired
    public CurrentRatioCrawler(FxssiClient fxssiClient, CurrentRatioFacade currentRatioFacade) {
        super(1);
        this.fxssiClient = fxssiClient;
        this.currentRatioFacade = currentRatioFacade;
    }

    @Override
    public void execute() {
        try {
            CurrentRatio currentRatio = fxssiClient.getDataFromFxssi();
            if (currentRatio != null) {
                currentRatioFacade.add(currentRatio);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
