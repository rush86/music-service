package com.musicservice.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverArtArchiveResponse {

  String release;
  List<CoverArtArchiveImage> images;

  @Value
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CoverArtArchiveImage {
    private final String id;
    private final String image;
    private final boolean front;
    private final boolean back;
  }
}
