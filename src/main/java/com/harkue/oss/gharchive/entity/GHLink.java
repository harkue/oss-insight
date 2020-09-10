package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class GHLink {
    @JsonDeserialize(as = GHhtml.class)
    private GHhtml html;

    @JsonDeserialize(as = GHPullRequest.class)
    @JsonProperty("pull_request")
    private GHPullRequest pullRequest;
}
