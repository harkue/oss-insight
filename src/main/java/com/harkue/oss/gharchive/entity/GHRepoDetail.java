package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GHRepoDetail {
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("private")
    private Boolean isPrivate;
    private GHUserDetail owner;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String description;
    private Boolean fork;
    private String url;
    @JsonProperty("forks_url")
    private String forksUrl;
    @JsonProperty("keys_url")
    private String keys_url;
    @JsonProperty("collaborators_url")
    private String collaboratorsUrl;
    @JsonProperty("teams_url")
    private String teams_url;
    @JsonProperty("hooks_url")
    private String hooks_url;
    @JsonProperty("issue_events_url")
    private String issue_events_url;
    @JsonProperty("events_url")
    private String events_url;
    @JsonProperty("assignees_url")
    private String assignees_url;
    @JsonProperty("branches_url")
    private String branches_url;
    @JsonProperty("tags_url")
    private String tags_url;
    @JsonProperty("blobs_url")
    private String blobs_url;
    @JsonProperty("git_tags_url")
    private String git_tags_url;
    @JsonProperty("git_refs_url")
    private String git_refs_url;
    @JsonProperty("trees_url")
    private String trees_url;
    @JsonProperty("statuses_url")
    private String statuses_url;
    @JsonProperty("languages_url")
    private String languages_url;
    @JsonProperty("stargazers_url")
    private String stargazers_url;
    @JsonProperty("contributors_url")
    private String contributors_url;
    @JsonProperty("subscribers_url")
    private String subscribers_url;
    @JsonProperty("subscription_url")
    private String subscription_url;
    @JsonProperty("commits_url")
    private String commits_url;
    @JsonProperty("git_commits_url")
    private String git_commits_url;
    @JsonProperty("comments_url")
    private String comments_url;
    @JsonProperty("issue_comment_url")
    private String issue_comment_url;
    @JsonProperty("contents_url")
    private String contents_url;
    @JsonProperty("compare_url")
    private String compare_url;
    @JsonProperty("merges_url")
    private String merges_url;
    @JsonProperty("archive_url")
    private String archive_url;
    @JsonProperty("downloads_url")
    private String downloads_url;
    @JsonProperty("issues_url")
    private String issues_url;
    @JsonProperty("pulls_url")
    private String pulls_url;
    @JsonProperty("milestones_url")
    private String milestones_url;
    @JsonProperty("notifications_url")
    private String notifications_url;
    @JsonProperty("labels_url")
    private String labels_url;
    @JsonProperty("releases_url")
    private String releases_url;
    @JsonProperty("deployments_url")
    private String deployments_url;
    @JsonProperty("created_at")
    private String created_at;
    @JsonProperty("updated_at")
    private String updated_at;
    @JsonProperty("pushed_at")
    private String pushed_at;
    @JsonProperty("git_url")
    private String git_url;
    @JsonProperty("ssh_url")
    private String ssh_url;
    @JsonProperty("clone_url")
    private String clone_url;
    @JsonProperty("svn_url")
    private String svn_url;
    private String homepage;
    private Integer size;
    @JsonProperty("stargazers_count")
    private Integer stargazers_count;
    @JsonProperty("watchers_count")
    private Integer watchers_count;
    private String language;
    @JsonProperty("has_issues")
    private Boolean has_issues;
    @JsonProperty("has_projects")
    private Boolean has_projects;
    @JsonProperty("has_downloads")
    private Boolean has_downloads;
    @JsonProperty("has_wiki")
    private Boolean has_wiki;
    @JsonProperty("has_pages")
    private Boolean has_pages;
    @JsonProperty("forks_count")
    private Integer forks_count;

    @JsonProperty("mirror_url")
    private String mirror_url;
    private Boolean archived;
    private Boolean disabled;
    @JsonProperty("open_issues_count")
    private Integer open_issues_count;
    private GHLicense license;
    private Integer forks;
    @JsonProperty("open_issues")
    private Integer openIssues;
    private Integer watchers;
    @JsonProperty("default_branch")
    private String defaultBranch;
}
