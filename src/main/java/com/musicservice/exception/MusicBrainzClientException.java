package com.musicservice.exception;

import lombok.Getter;

@Getter
public class MusicBrainzClientException extends RuntimeException {

    public MusicBrainzClientException(String message) {
        super(message);
    }
}
