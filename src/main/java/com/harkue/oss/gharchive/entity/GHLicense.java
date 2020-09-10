package com.harkue.oss.gharchive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GHLicense {
    private String key;
    private String name;
    @JsonProperty("spdx_id")
    private String spdxId;
    private String url;
    @JsonProperty("node_id")
    private String node_id;
}
