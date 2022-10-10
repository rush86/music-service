package com.musicservice.api.dto;

import java.util.List;

public record MusicArtistDTO(String mbid, String name, String gender, String country,
                             String disambiguation, String description,
                             List<MusicAlbumDTO> albums) {

}
