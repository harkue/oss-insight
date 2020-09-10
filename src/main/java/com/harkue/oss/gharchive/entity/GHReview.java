package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class GHReview {
    private Integer id;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonDeserialize(as = GHUser.class)
    private GHUser user;

    private String body;

    @JsonProperty("commit_id")
    private String commitId;

    @JsonProperty("submitted_at")
    private String submittedAt;
    private String state;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("pull_request_url")
    private String pullRequestUrl;

    @JsonProperty("author_association")
    private String authorAssociation;

    @JsonProperty("_links")
    private GHLink links;
}
