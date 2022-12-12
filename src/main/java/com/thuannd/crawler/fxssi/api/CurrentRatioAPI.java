package com.thuannd.crawler.fxssi.api;

import com.thuannd.crawler.fxssi.entity.CurrentRatio;
import com.thuannd.crawler.fxssi.model.CurrentRatioResponse;
import com.thuannd.crawler.fxssi.model.Page;
import com.thuannd.crawler.fxssi.model.SearchCurrentRatioRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/current-ratio")
public interface CurrentRatioAPI {

    @PostMapping
    void add(@RequestBody CurrentRatio currentRatio);

    @GetMapping("/search")
    Page<CurrentRatioResponse> search(@ModelAttribute SearchCurrentRatioRequest req);

}
