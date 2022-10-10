package com.musicservice.core.domain;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MusicArtist {

  private final UUID mbid;
  private final String name;
  private final String gender;
  private final String country;
  private final String disambiguation;
  private final String description;
  private final List<MusicAlbum> musicAlbums;

}
