package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GHPayload {
    @JsonProperty("push_id")
    private String pushId;
    private Integer size;
    @JsonProperty("distinct_size")
    private Integer distinctSize;
    private String ref;
    private String head;
    private String before;
    private List<GHCommit> commits;
    private GHReview review;
    @JsonProperty("pull_request")
    private GHPullRequest pullRequest;
    private String action;
}
