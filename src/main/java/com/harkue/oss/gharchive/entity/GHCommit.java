package com.harkue.oss.gharchive.entity;

import lombok.Data;

@Data
public class GHCommit {
    private String sha;
    private GHAuthor author;
    private String message;
    private Boolean distinct;
    private String url;
}
