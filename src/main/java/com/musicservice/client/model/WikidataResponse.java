package com.musicservice.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikidataResponse {

  private final String title;
  private final String id;
  private final SiteLink sitelinks;

  @Value
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SiteLink {
    private final SiteLinkDetails enwiki;
    private final SiteLinkDetails enwikinews;
  }

  @Value
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SiteLinkDetails {
    private final String site;
    private final String title;
    private final String url;
  }
}
