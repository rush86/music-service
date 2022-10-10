package com.musicservice.api;

import static com.musicservice.mapper.MusicArtistDTOMapper.mapToMusicArtistDTO;

import com.musicservice.api.dto.MusicArtistDTO;
import com.musicservice.core.service.MusicArtistService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musify/")
@Slf4j
public class MusicAPI {

    private final MusicArtistService musicArtistService;

    public MusicAPI(MusicArtistService musicArtistService) {
        this.musicArtistService = musicArtistService;
    }

    @GetMapping("/music-artist/details/{mbid}")
    public ResponseEntity<MusicArtistDTO> getMusicArtistDetailsByMBID(@PathVariable("mbid") String musicBrainzId) {
        var musicArtist = mapToMusicArtistDTO(musicArtistService.getMusicArtistDetailsByMbid(UUID.fromString(musicBrainzId)));
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(24, TimeUnit.HOURS))
            .body(musicArtist);
    }
}