package com.musicservice.mapper;

import com.musicservice.api.dto.MusicAlbumDTO;
import com.musicservice.api.dto.MusicArtistDTO;
import com.musicservice.core.domain.MusicAlbum;
import com.musicservice.core.domain.MusicArtist;
import java.util.List;
import java.util.stream.Collectors;

public class MusicArtistDTOMapper {

  public static MusicArtistDTO mapToMusicArtistDTO(MusicArtist musicArtist) {
    return new MusicArtistDTO(musicArtist.getMbid().toString(), musicArtist.getName(), musicArtist.getGender(),
        musicArtist.getCountry(), musicArtist.getDisambiguation(), musicArtist.getDescription(),
        mapToMusicAlbumDTOs(musicArtist.getMusicAlbums()));
  }

  private static List<MusicAlbumDTO> mapToMusicAlbumDTOs(List<MusicAlbum> musicAlbums) {
    return musicAlbums.stream()
        .map(MusicArtistDTOMapper::mapToMusicAlbumDTO)
        .collect(Collectors.toList());
  }

  private static MusicAlbumDTO mapToMusicAlbumDTO(MusicAlbum musicAlbum) {
    return new MusicAlbumDTO(musicAlbum.id().toString(), musicAlbum.title(), musicAlbum.imageUrl());
  }
}
