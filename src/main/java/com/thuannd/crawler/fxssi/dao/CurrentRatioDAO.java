package com.thuannd.crawler.fxssi.dao;

import com.thuannd.crawler.fxssi.model.CurrentRatioResponse;
import com.thuannd.crawler.fxssi.model.Page;
import com.thuannd.crawler.fxssi.model.SearchCurrentRatioRequest;

public interface CurrentRatioDAO {

    Page<CurrentRatioResponse> search(SearchCurrentRatioRequest request);

}
