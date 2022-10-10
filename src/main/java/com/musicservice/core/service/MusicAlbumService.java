package com.musicservice.core.service;

import com.musicservice.client.CoverArtArchiveClient;
import com.musicservice.client.model.CoverArtArchiveResponse;
import com.musicservice.client.model.MusicBrainzArtist;
import com.musicservice.core.domain.MusicAlbum;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MusicAlbumService {

  private final CoverArtArchiveClient coverArtArchiveClient;

  public MusicAlbumService(CoverArtArchiveClient coverArtArchiveClient) {
    this.coverArtArchiveClient = coverArtArchiveClient;
  }

  public List<MusicAlbum> getMusicAlbumsFromMusicBrainz(MusicBrainzArtist musicBrainzArtist) {
    return musicBrainzArtist.getReleaseGroups().stream()
        .filter(release -> "Album".equalsIgnoreCase(release.getPrimaryType()))
        .map(release -> new MusicAlbum(UUID.fromString(release.getId()), release.getTitle(), getCoverArtImageUrlByMbid(release.getId())))
        .collect(Collectors.toList());
  }

  private String getCoverArtImageUrlByMbid(String id) {

    String coverImageUrl = "";
    try {
      CoverArtArchiveResponse coverArtByMbid = coverArtArchiveClient.getCoverArtByMbid(id);
      coverImageUrl = coverArtByMbid.getImages().stream()
          .filter(image -> image.isFront())
          .findFirst().orElseThrow().getImage();
      return coverImageUrl;
    } catch (Exception e) {
      return coverImageUrl;
    }
  }
}