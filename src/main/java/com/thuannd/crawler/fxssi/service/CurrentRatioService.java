package com.thuannd.crawler.fxssi.service;

import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.repository.CurrentRatioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CurrentRatioService {

    private final CurrentRatioRepository currentRatioRepository;

    public void add(CurrentRatio currentRatio) {
        currentRatioRepository.save(currentRatio);
    }

}
