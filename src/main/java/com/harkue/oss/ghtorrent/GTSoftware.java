package com.harkue.oss.ghtorrent;

import lombok.Data;

@Data
public class GTSoftware {
    private String name;
    private String softwareNo;
    private String version;
    private String versionNo;
    private String repoUrl;
    private String repoApiUrl;
    private String projectId;

    public String toString() {
        return String.format("name = %s, software_no = %s, version = %s, version_no = %s, repo_url = %s, repo_api_url = %s, project_id = %s", name, softwareNo, version, versionNo, repoUrl, repoApiUrl, projectId);
    }
}
