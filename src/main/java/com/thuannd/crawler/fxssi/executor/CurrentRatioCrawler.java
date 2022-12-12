package com.thuannd.crawler.fxssi.executor;

import com.thuannd.crawler.fxssi.client.FxssiClient;
import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.facade.CurrentRatioFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrentRatioCrawler extends ExecutorJob {

    private final FxssiClient fxssiClient;
    private final CurrentRatioFacade currentRatioFacade;

    private final boolean enable;

    @Autowired
    public CurrentRatioCrawler(Environment environment, FxssiClient fxssiClient, CurrentRatioFacade currentRatioFacade) {
        super(5 * 60);
        this.fxssiClient = fxssiClient;
        this.currentRatioFacade = currentRatioFacade;
        this.enable = "true".equalsIgnoreCase(environment.getProperty("fxssi.enable"));
    }

    @Override
    public void execute() {
        if (!enable) {
            log.info("CurrentRatioCrawler is disable by config!");
            return;
        }
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