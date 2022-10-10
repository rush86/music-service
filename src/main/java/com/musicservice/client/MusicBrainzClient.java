package com.musicservice.client;

import com.musicservice.client.model.MusicBrainzArtist;
import com.musicservice.exception.MusicBrainzClientException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class MusicBrainzClient extends AbstractRestClient {

  private final String MUSIC_BRAINZ_BASE_URI;
  private final String ARTIST_PATH = "/artist/{mbid}";

  public MusicBrainzClient(RestTemplate restTemplate,
      @Value("${service.music-brainz.api.url}") String music_brainz_base_uri) {
    super(restTemplate);
    MUSIC_BRAINZ_BASE_URI = music_brainz_base_uri;
  }

  public MusicBrainzArtist getArtistByMbid(UUID mbid) {
    try {
      var apiUri = MUSIC_BRAINZ_BASE_URI + UriComponentsBuilder.fromPath(ARTIST_PATH).queryParam("inc", "url-rels+release-groups")
          .queryParam("fmt", "json")
          .build(mbid.toString());
      return get(apiUri, MusicBrainzArtist.class);
    } catch (Exception e) {
      log.error("Error: ", e);
      throw new MusicBrainzClientException(e.getMessage());
    }
  }
}
