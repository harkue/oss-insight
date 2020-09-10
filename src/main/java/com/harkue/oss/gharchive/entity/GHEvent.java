package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class GHEvent {
    private String id;
    private String type;

    @JsonDeserialize(as = GHActor.class)
    private GHActor actor;

    @JsonDeserialize(as = GHRepo.class)
    private GHRepo repo;

    @JsonDeserialize(as = GHPayload.class)
    private GHPayload payload;

    @JsonProperty("public")
    private Boolean isPublic;

    @JsonProperty("created_at")
    private String createdAt;
}
