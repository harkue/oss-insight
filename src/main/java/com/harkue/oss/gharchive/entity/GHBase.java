package com.harkue.oss.gharchive.entity;

import lombok.Data;

@Data
public class GHBase {
    private String label;
    private String ref;
    private String sha;
    private GHUser user;
    private GHRepoDetail repo;
}
