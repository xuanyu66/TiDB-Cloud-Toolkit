package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class ClusterVO {

    private String id;
    private String name;
    private String status;
    private String secondary_status;
    private String version;
    private String provider;
    private String region;
    private String profile_name;
    private String created_at;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setSecondary_status(String secondary_status) {
        this.secondary_status = secondary_status;
    }

    public String getSecondary_status() {
        return secondary_status;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }
}