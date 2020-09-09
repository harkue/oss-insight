package com.harkue.oss.ghtorrent;

import lombok.Data;

@Data
public class GTProject {
    private String id;
    private String url;
    private String ownerId;
    private String name;
    private String description;
    private String language;
    private String createdAt;
    private String forkedFrom;
    private String deleted;
    private String updatedAt;

    public String toString() {
        return String.format("id = %s, url = %s, owner_id = %s, name = %s, description = %s, language = %s, created_at = %s, forked_from = %s, deleted = %s, updated_at = %s",
                id, url, ownerId, name, description, language, createdAt, forkedFrom, deleted, updatedAt);
    }
}
