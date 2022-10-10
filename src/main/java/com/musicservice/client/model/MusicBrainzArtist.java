package com.musicservice.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzArtist {

  private final String type;
  private final String name;
  private final String gender;
  private final String disambiguation;
  private final String country;
  private final List<Relations> relations;
  @JsonProperty(value = "release-groups")
  private final List<ReleaseGroups> releaseGroups;

  @Value
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ReleaseGroups {

    private final String id;
    private final String title;
    @JsonProperty(value = "primary-type")
    private final String primaryType; //primary-type: Album
  }

  @Value
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Relations {

    private final String type; // wikidata
    private final ResourceUrl url;

  }

  @Value
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ResourceUrl {

    private final String resource;
    private final String id;

  }
}
