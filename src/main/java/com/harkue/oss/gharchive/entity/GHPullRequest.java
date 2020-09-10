package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GHPullRequest {
    private String url;
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("diff_url")
    private String diffUrl;
    @JsonProperty("patch_url")
    private String patchUrl;
    @JsonProperty("issue_url")
    private String issueUrl;
    private Integer number;
    private String state;
    private Boolean locked;
    private String title;
    private GHUser user;
    private String body;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private String closedAt;
    @JsonProperty("merged_at")
    private String mergedAt;
    @JsonProperty("merge_commit_sha")
    private String mergeCommitSha;
    private String assignee;
    private List<String> assignees = new ArrayList<>();
    @JsonProperty("requested_reviewers")
    private List<String> requestedReviewers = new ArrayList<>();
    @JsonProperty("requested_teams")
    private List<String> requestedTeams = new ArrayList<>();
    private List<String> labels = new ArrayList<>();
    private String milestone;
    private Boolean draft;
    @JsonProperty("commits_url")
    private String commitsUrl;
    @JsonProperty("review_comments_url")
    private String reviewCommentsUrl;
    @JsonProperty("review_comment_url")
    private String reviewCommentUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("statuses_url")
    private String statusesUrl;
    private GHHead head;
    private GHBase base;
    @JsonProperty("_links")
    private GHLink links;

    private String href;
}
