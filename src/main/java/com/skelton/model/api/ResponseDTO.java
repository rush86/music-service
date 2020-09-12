package com.skelton.model.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder(builderClassName = "Builder")
@Value
@JsonDeserialize(builder = ResponseDTO.Builder.class)
public class ResponseDTO {

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }

    @NonNull
    private String message;
}