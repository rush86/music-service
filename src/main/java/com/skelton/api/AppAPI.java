package com.skelton.api;

import com.skelton.aggregator.AppAggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AppAPI {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AppAggregator appAggregator;

    @Autowired
    public AppAPI(AppAggregator appAggregator) {
        this.appAggregator = appAggregator;
    }


}