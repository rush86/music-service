package com.skelton.api;

import com.skelton.aggregator.AppAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Slf4j
public class AppAPI {

    private final AppAggregator appAggregator;

    public AppAPI(AppAggregator appAggregator) {
        this.appAggregator = appAggregator;
    }

    @GetMapping("/test")
    public String testApi() {
        return "Hello World from Test Controller!";
    }
}