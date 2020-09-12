package com.skelton.aggregator;

import com.skelton.service.AppService;
import org.springframework.stereotype.Component;

@Component
public class AppAggregator {

    private final AppService appService;


    public AppAggregator(AppService appService) {
        this.appService = appService;
    }
}