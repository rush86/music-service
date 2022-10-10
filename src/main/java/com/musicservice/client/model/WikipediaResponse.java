package com.musicservice.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaResponse {

  private final Long pageid;
  private final String lang;
  private final String tid;
  private final String description;
  private final String extract;

}
