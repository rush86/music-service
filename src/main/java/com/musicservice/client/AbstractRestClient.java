package com.musicservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public abstract class AbstractRestClient {

  private final RestTemplate restTemplate;

  protected AbstractRestClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public <S> S get(String path, Class<S> responseType) {
    log.info("Making GET request to path={}", path);
    return restTemplate.getForObject(path, responseType);
  }

  public <T, R> R post(String path, T body, Class<R> responseType) {
    log.info("Making POST request to path={} with body={}", path, body);
    return restTemplate.postForObject(path, body, responseType);
  }

  public <T> T post(String path, Class<T> responseType) {
    log.info("Making POST request to path={} with no body", path);
    return restTemplate.postForObject(path, new Object(), responseType);
  }
}
