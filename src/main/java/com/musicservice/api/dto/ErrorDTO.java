package com.musicservice.api.dto;

import lombok.NonNull;

public record ErrorDTO(@NonNull ErrorCode errorCode, String stackTrace,
                       @NonNull String message) { }