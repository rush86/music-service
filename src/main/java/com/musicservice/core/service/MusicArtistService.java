package com.musicservice.core.service;

import com.musicservice.client.MusicBrainzClient;
import com.musicservice.client.model.MusicBrainzArtist.Relations;
import com.musicservice.client.model.MusicBrainzArtist.ResourceUrl;
import com.musicservice.core.domain.MusicArtist;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MusicArtistService {

  private final MusicBrainzClient musicBrainzClient;
  private final WikipediaDataService wikipediaDataService;
  private final MusicAlbumService musicAlbumService;

  public MusicArtistService(MusicBrainzClient musicBrainzClient, WikipediaDataService wikipediaDataService,
      MusicAlbumService musicAlbumService) {
    this.musicBrainzClient = musicBrainzClient;
    this.wikipediaDataService = wikipediaDataService;
    this.musicAlbumService = musicAlbumService;
  }

  public MusicArtist getMusicArtistDetailsByMbid(UUID mbid) {

    var artistData = musicBrainzClient.getArtistByMbid(mbid);
    var wikidataUrl = artistData.getRelations().stream()
        .filter(relations -> "wikidata".equals(relations.getType()))
        .map(Relations::getUrl)
        .map(ResourceUrl::getResource).findFirst();

    String artistDescription = "";
    if (Objects.nonNull(wikidataUrl) && wikidataUrl.isPresent()) {
       artistDescription = wikipediaDataService.getWikipediaDescription(wikidataUrl.get());
    }

    return MusicArtist.builder()
        .mbid(mbid)
        .name(artistData.getName())
        .gender(artistData.getGender())
        .disambiguation(artistData.getDisambiguation())
        .country(artistData.getCountry())
        .description(artistDescription)
        .musicAlbums(musicAlbumService.getMusicAlbumsFromMusicBrainz(artistData))
        .build();
  }
}